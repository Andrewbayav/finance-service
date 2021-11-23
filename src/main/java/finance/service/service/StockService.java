package finance.service.service;

import finance.service.entity.PortfolioEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import finance.service.dto.YahooStockDto;
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

    @Value("${yahoo.params}")
    private String yahooParams;

    @Value("${tinkoff.api.url}")
    private String tinkoffApiUrl;

    @Value("${tinkoff.api.url.portfolio}")
    private String portfolioEndpoint;

    @Autowired
    private final StockRepository stockRepository;

    public void saveStock(Stock stock){
        stockRepository.save(stock);
    }

    public YahooStockDto getYahooStockInfo(String ticker) throws IOException, InterruptedException {
        log.info("Send request for ticker: " + ticker);
        String responseBody = HttpUtil.sendYahooTickerRequest(yahooApiUrl, ticker, yahooParams);
        return JsonParserUtil.jsonObjectToStockDto(responseBody);
    }

    public double getCurrencyPairRate(String pairTicker) throws IOException, InterruptedException {
        YahooStockDto yahooStockDto = getYahooStockInfo(pairTicker);
        return  yahooStockDto.getPrice();
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
            entries.add(new PortfolioEntry(jsonObject, USD_RUB, EUR_RUB, USD_EUR));
        }
        return entries;
    }
}
