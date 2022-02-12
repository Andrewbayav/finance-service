package invest.service.service;

import invest.service.dto.representation.OverviewDto;
import invest.service.entity.AnalysisProcessingEntity;
import invest.service.entity.AnalysisEntity;
import invest.service.entity.yahoo.ExchangeEntity;
import invest.service.entity.PortfolioEntity;
import invest.service.entity.TcsTickerEntity;
import invest.service.entity.TickerDictionaryEntity;
import invest.service.entity.yahoo.YahooFinancialEntity;
import invest.service.entity.yahoo.YahooStatisticsEntity;
import invest.service.entity.yahoo.YahooSummaryEntity;
import invest.service.jdbcMapper.OverviewMapper;
import invest.service.repository.AnalysisProcessingRepository;
import invest.service.repository.AnalysisRepository;
import invest.service.repository.yahoo.ExchangeRepository;
import invest.service.repository.PortfolioRepository;
import invest.service.repository.TcsRepository;
import invest.service.repository.TickerDictionaryRepository;
import invest.service.repository.yahoo.YahooFinancialRepository;
import invest.service.repository.yahoo.YahooStatisticsRepository;
import invest.service.repository.yahoo.YahooSummaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;


import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class RepositoryService {

    private final PortfolioRepository portfolioRepository;
    private final TcsRepository tcsRepository;
    private final YahooFinancialRepository yahooFinancialRepository;
    private final YahooSummaryRepository yahooSummaryRepository;
    private final YahooStatisticsRepository yahooStatisticsRepository;
    private final ExchangeRepository exchangeRepository;
    private final JdbcTemplate jdbcTemplate;
    private final TickerDictionaryRepository dictionaryRepository;
    private final AnalysisRepository analysisRepository;
    private final AnalysisProcessingRepository analysisProcessingRepository;

    @Autowired
    public RepositoryService(
            PortfolioRepository portfolioRepository,
            TcsRepository tcsRepository,
            YahooFinancialRepository yahooFinancialRepository,
            YahooSummaryRepository yahooSummaryRepository,
            YahooStatisticsRepository yahooStatisticsRepository,
            ExchangeRepository exchangeRepository,
            JdbcTemplate jdbcTemplate,
            TickerDictionaryRepository dictionaryRepository,
            AnalysisRepository analysisRepository,
            AnalysisProcessingRepository analysisProcessingRepository) {
        this.portfolioRepository = portfolioRepository;
        this.tcsRepository = tcsRepository;
        this.yahooFinancialRepository = yahooFinancialRepository;
        this.yahooSummaryRepository = yahooSummaryRepository;
        this.yahooStatisticsRepository = yahooStatisticsRepository;
        this.exchangeRepository = exchangeRepository;
        this.jdbcTemplate = jdbcTemplate;
        this.dictionaryRepository = dictionaryRepository;
        this.analysisRepository = analysisRepository;
        this.analysisProcessingRepository = analysisProcessingRepository;
    }

    public void saveTcsEntity(TcsTickerEntity entity) {
        this.tcsRepository.save(entity);
    }

    public void savePortfolioEntity(PortfolioEntity entity) {
        this.portfolioRepository.save(entity);
    }

    public void saveExchangeEntity(ExchangeEntity entity) {
        this.exchangeRepository.save(entity);
    }

    public void saveYahooFinancialEntity(YahooFinancialEntity entity) {
        this.yahooFinancialRepository.save(entity);
    }

    public void saveYahooStatisticsEntity(YahooStatisticsEntity entity) {
        this.yahooStatisticsRepository.save(entity);
    }

    public void saveYahooSummaryEntity(YahooSummaryEntity entity) {
        this.yahooSummaryRepository.save(entity);
    }

    public void saveAnalysisEntity(AnalysisEntity entity) {
        this.analysisRepository.save(entity);
    }

    public List<AnalysisEntity> getAllAnalysisEntities() {
        return this.analysisRepository.findAll();
    }

    public void saveAnalysisProcessingEntity(AnalysisProcessingEntity entity) {
        this.analysisProcessingRepository.save(entity);
    }

    public PortfolioEntity getLatestPortfolioEntity () {
        return portfolioRepository.findFirstByOrderByTimestampDesc();
    }

    public TickerDictionaryEntity getTickerFromDictionary(String tcsTicker) {
        return dictionaryRepository.findByTcsTicker(tcsTicker);
    }

    public Map<String, String> getAllTickersFromDictionary() {
        Map<String, String> map = new HashMap<>();
        dictionaryRepository.findAll().stream().forEach(x -> map.put(x.getYahooTicker(), x.getName()));
        return map;
    }

    public List<OverviewDto> getOverviewDtos (UUID uuid) {
        String query = String.format("SELECT " +
                "name, ticker, average_position_price, balance, currency, " +
                "expected_yield, instrument_type, recommendation_mean, " +
                "return_on_equity, price_to_book, enterprise_value, dividend_yield, " +
                "trailingpe, price_to_sales_trailing12months, market_cap, rate, timestamp " +
                "from financial_overview " +
                "WHERE uuid = '%s' ORDER BY instrument_type", uuid);
        List<OverviewDto> list = jdbcTemplate.query(query, new OverviewMapper());
        return list;
    }

    public void prepareView () {
        String query =
                "CREATE or REPLACE VIEW financial_overview\n" +
                "AS SELECT\n" +
                "    t.id, t.uuid, t.name, t.ticker, t.average_position_price, t.balance, t.currency, t.expected_yield, t.instrument_type,\n" +
                "    f.recommendation_mean, f. return_on_equity,\n" +
                "    st.price_to_book, st.enterprise_value,\n" +
                "    s.dividend_yield, s.trailingpe, s.price_to_sales_trailing12months, s.market_cap,\n" +
                "    u.rate as rate,\n" +
                "    p.timestamp\n" +
                "FROM tcs t\n" +
                "FULL JOIN usd_exchange u on u.currency = t.currency and u.uuid = t.uuid\n" +
                "JOIN yahoo_financial f on f.ticker = t.ticker and f.uuid = t.uuid\n" +
                "JOIN yahoo_statistics st on st.ticker = f.ticker and st.uuid = f.uuid\n" +
                "JOIN yahoo_summary s on st.ticker = s.ticker and st.uuid = s.uuid\n" +
                "JOIN portfolio p on p.uuid = s.uuid;\n";
        jdbcTemplate.execute(query);
    }


    // TODO: заплатка для сохранения тикеров в справочник - на этом этапе не интегрируем
    public void saveTickersToDictionary(List<TickerDictionaryEntity> entities) {
        this.dictionaryRepository.saveAll(entities);
    }

}
