package finance.service.service;

import finance.service.dto.OverviewDto;
import finance.service.dto.TcsTickerDto;
import finance.service.dto.YahooFinancialDto;
import finance.service.dto.YahooStatisticsDto;
import finance.service.dto.YahooSummaryDto;
import finance.service.entity.PortfolioEntity;
import finance.service.entity.TcsTickerEntity;
import finance.service.entity.YahooFinancialEntity;
import finance.service.entity.YahooStatisticsEntity;
import finance.service.entity.YahooSummaryEntity;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
public class StockService {

    private final TcsService tcsService;
    private final YahooStockService yahooStockService;
    private final RepositoryService repositoryService;

    @Autowired
    public StockService(
            TcsService tcsService,
            YahooStockService yahooStockService,
            RepositoryService repositoryService) {
        this.tcsService = tcsService;
        this.yahooStockService = yahooStockService;
        this.repositoryService = repositoryService;
    }

    public List<OverviewDto> getLatestAvailableData() {
        PortfolioEntity latest = repositoryService.getLatestPortfolioEntity();
        return repositoryService.getOverviewDtos(latest.getUuid());
    }

    public List<OverviewDto> getNewData(String token) throws JSONException, IOException, InterruptedException {
        ArrayList<TcsTickerDto> portfolioDtos = tcsService.getPortfolio(token);
        String tickers = portfolioDtos.stream().map(x -> x.getTicker()).collect(Collectors.joining(" "));

        List<YahooFinancialDto> financialDtos = yahooStockService.getFinancialDtoList(tickers);
        List<YahooStatisticsDto> statisticsDtos = yahooStockService.getStatisticsDtoList(tickers);
        List<YahooSummaryDto> summaryDtos = yahooStockService.getSummaryDtoList(tickers);

        UUID uuid = UUID.randomUUID();
        repositoryService.savePortfolioEntity(new PortfolioEntity(uuid));

        portfolioDtos.stream().forEach(x -> repositoryService.saveTcsEntity(new TcsTickerEntity(uuid, x)));
        financialDtos.stream().forEach(x -> repositoryService.saveYahooFinancialEntity(new YahooFinancialEntity(uuid, x)));
        statisticsDtos.stream().forEach(x -> repositoryService.saveYahooStatisticsEntity(new YahooStatisticsEntity(uuid, x)));
        summaryDtos.stream().forEach(x -> repositoryService.saveYahooSummaryEntity(new YahooSummaryEntity(uuid, x)));

        return repositoryService.getOverviewDtos(uuid);
    }
}
