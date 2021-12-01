package finance.service.service;

import finance.service.dto.YahooStockFinancialDto;
import finance.service.dto.YahooStockStatisticsDto;
import finance.service.dto.YahooStockSummaryDto;
import finance.service.entity.PortfolioEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import finance.service.dto.YahooPriceDto;
import finance.service.entity.Stock;
import finance.service.repository.StockRepository;
import finance.service.utils.HttpUtil;
import finance.service.utils.JsonParserUtil;

import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
public class StockService {

    @Value("${yahoo.api.url}")
    private String yahooApiUrl;

    @Value("${yahoo.params.price}")
    private String yahooPrice;

    @Value("${yahoo.params.statistics}")
    private String yahooStatistics;

    @Value("${yahoo.params.yahoo.params.summaryDetail}")
    private String yahooSummaryDetail;

    @Value("${yahoo.params.financialData}")
    private String yahooFinancialData;

    @Value("${tinkoff.api.url}")
    private String tinkoffApiUrl;

    @Value("${tinkoff.api.url.portfolio}")
    private String portfolioEndpoint;

    @Autowired
    private final StockRepository stockRepository;

    public void saveStock(Stock stock){
        stockRepository.save(stock);
    }

    // используем для получения курса валютных пар
    public YahooPriceDto getYahooStockPrice(String ticker) throws IOException, InterruptedException {
        log.info("Send price request for ticker: " + ticker);
        String responseBody = HttpUtil.sendYahooTickerRequest(yahooApiUrl, ticker, yahooPrice);
        return JsonParserUtil.jsonObjectToStockDto(responseBody);
    }

    public YahooStockStatisticsDto getYahooStockStatistics(String ticker) throws IOException, InterruptedException {
        log.info("Send statistics request for ticker: " + ticker);
        String yahooStatisticsJson = HttpUtil.sendYahooTickerRequest(yahooApiUrl, ticker, yahooStatistics);
        return JsonParserUtil.jsonToYahooStatisticsObj(ticker, yahooStatisticsJson);
    }

    public YahooStockFinancialDto getYahooFinancialData(String ticker) throws IOException, InterruptedException {
        log.info("Send financial request for ticker: " + ticker);
        String yahooFinancialJson = HttpUtil.sendYahooTickerRequest(yahooApiUrl, ticker, yahooFinancialData);
        return JsonParserUtil.jsonToYahooFinancialObj(ticker, yahooFinancialJson);
    }

    public YahooStockSummaryDto getYahooSummaryDetails(String ticker) throws IOException, InterruptedException {
        log.info("Send summary request for ticker: " + ticker);
        String yahooSummaryJson = HttpUtil.sendYahooTickerRequest(yahooApiUrl, ticker, yahooSummaryDetail);
        return JsonParserUtil.jsonToYahooSummaryObj(ticker, yahooSummaryJson);
    }

    public double getCurrencyPairRate(String pairTicker) throws IOException, InterruptedException {
        YahooPriceDto yahooPriceDto = getYahooStockPrice(pairTicker);
        return  yahooPriceDto.getPrice();
    }

    public ArrayList getPortfolio(String token) throws JSONException, IOException, InterruptedException {
        String jsonData = HttpUtil.sendTinkoffPortfolioRequest(tinkoffApiUrl + portfolioEndpoint, token);
        JSONArray portfolio = new JSONObject(jsonData).getJSONObject("payload").getJSONArray("positions");

        ArrayList<PortfolioEntry> entries = new ArrayList<>();
        double EUR_RUB = getCurrencyPairRate("EURRUB=X");
        double USD_RUB = getCurrencyPairRate("RUB=X");
        double USD_EUR = getCurrencyPairRate("EUR=X");


        for (int i = 0; i < portfolio.length(); i++) {
            JSONObject jsonObject = portfolio.getJSONObject(i);
            PortfolioEntry portfolioEntry = new PortfolioEntry(jsonObject, USD_RUB, EUR_RUB, USD_EUR);
            portfolioEntry.addStatistics(getYahooStockStatistics(portfolioEntry.getTicker()));
            entries.add(portfolioEntry);
        }
        return entries;
    }
}
