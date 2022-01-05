package finance.service.service;

import finance.service.entity.PortfolioEntity;
import finance.service.entity.TcsTickerEntity;
import finance.service.entity.YahooFinancialEntity;
import finance.service.entity.YahooStatisticsEntity;
import finance.service.entity.YahooSummaryEntity;
import finance.service.repository.PortfolioRepository;
import finance.service.repository.TcsRepository;
import finance.service.repository.YahooFinancialRepository;
import finance.service.repository.YahooStatisticsRepository;
import finance.service.repository.YahooSummaryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RepositoryService {

    private final PortfolioRepository portfolioRepository;
    private final TcsRepository tcsRepository;
    private final YahooFinancialRepository yahooFinancialRepository;
    private final YahooSummaryRepository yahooSummaryRepository;
    private final YahooStatisticsRepository yahooStatisticsRepository;

    @Autowired
    public RepositoryService(
            PortfolioRepository portfolioRepository,
            TcsRepository tcsRepository,
            YahooFinancialRepository yahooFinancialRepository,
            YahooSummaryRepository yahooSummaryRepository,
            YahooStatisticsRepository yahooStatisticsRepository) {
        this.portfolioRepository = portfolioRepository;
        this.tcsRepository = tcsRepository;
        this.yahooFinancialRepository = yahooFinancialRepository;
        this.yahooSummaryRepository = yahooSummaryRepository;
        this.yahooStatisticsRepository = yahooStatisticsRepository;
    }

    public void saveTcsEntity(TcsTickerEntity entity) {
        this.tcsRepository.save(entity);
    }

    public void savePortfolioEntity(PortfolioEntity entity) {
        this.portfolioRepository.save(entity);
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
}
