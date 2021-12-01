package finance.service.service;

import finance.service.dto.OverviewDto;
import finance.service.entity.PortfolioEntity;
import finance.service.entity.TcsTickerEntity;
import finance.service.entity.YahooFinancialEntity;
import finance.service.entity.YahooStatisticsEntity;
import finance.service.entity.YahooSummaryEntity;
import finance.service.jdbcMapper.OverviewMapper;
import finance.service.repository.PortfolioRepository;
import finance.service.repository.TcsRepository;
import finance.service.repository.YahooFinancialRepository;
import finance.service.repository.YahooStatisticsRepository;
import finance.service.repository.YahooSummaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;


import java.util.UUID;

@Slf4j
@Service
public class RepositoryService {

    private final PortfolioRepository portfolioRepository;
    private final TcsRepository tcsRepository;
    private final YahooFinancialRepository yahooFinancialRepository;
    private final YahooSummaryRepository yahooSummaryRepository;
    private final YahooStatisticsRepository yahooStatisticsRepository;
    private final DataSource dataSource;

    @Autowired
    public RepositoryService(
            PortfolioRepository portfolioRepository,
            TcsRepository tcsRepository,
            YahooFinancialRepository yahooFinancialRepository,
            YahooSummaryRepository yahooSummaryRepository,
            YahooStatisticsRepository yahooStatisticsRepository, DataSource dataSource) {
        this.portfolioRepository = portfolioRepository;
        this.tcsRepository = tcsRepository;
        this.yahooFinancialRepository = yahooFinancialRepository;
        this.yahooSummaryRepository = yahooSummaryRepository;
        this.yahooStatisticsRepository = yahooStatisticsRepository;
        this.dataSource = dataSource;
    }

    public void saveTcsEntity(TcsTickerEntity entity) {
        this.tcsRepository.save(entity);
    }

    public void savePortfolioEntity(PortfolioEntity entity) {
        this.portfolioRepository.save(entity);
    }

    public PortfolioEntity getLatestPortfolioEntity () {
        return portfolioRepository.findFirstByOrderByTimestampDesc();
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

    public List<OverviewDto> getOverviewDtos (UUID uuid) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String query = String.format("SELECT " +
                "name, ticker, average_position_price, lots, currency, " +
                "expected_yield, instrument_type, recommendation_mean, " +
                "return_on_equity, price_to_book, enterprise_value, dividend_yield, " +
                "trailingpe, price_to_sales_trailing12months, market_cap from financial_overview " +
                "WHERE uuid = '%s' ORDER BY instrument_type", uuid);
        List<OverviewDto> list = jdbcTemplate.query(query, new OverviewMapper());
        return list;
    }
}
