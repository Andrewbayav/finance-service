package finance.service.service;

import finance.service.entity.TickerDictionaryEntity;
import finance.service.repository.TickerDictionaryRepository;
import finance.service.utils.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Service
@Slf4j
public class TickerDictionaryService {

    @Value("${tinkoff.api.url}")
    private String tinkoffApiUrl;

    @Value("${tinkoff.api.url.market.stocks}")
    private String marketStocks;

    @Value("${filepath}")
    private String filePath;

    private final TickerDictionaryRepository repository;

    @Autowired
    public TickerDictionaryService(TickerDictionaryRepository repository) {
        this.repository = repository;
    }

    //TODO: Причесать, чтобы стало красиво (в последнюю очередь).
    public void getMarketStocks(String token) throws JSONException, IOException, InterruptedException {
        String jsonData = HttpUtil.sendTinkoffPortfolioRequest(tinkoffApiUrl + marketStocks, token);
        ArrayList<TickerDictionaryEntity> list = new ArrayList<>();
        File file = new File(filePath);
        FileWriter fileWriter = null;
        fileWriter = new FileWriter(file);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        int k = 1;
        JSONArray portfolio = new JSONObject(jsonData).getJSONObject("payload").getJSONArray("instruments");
        for (int i = 0; i < portfolio.length(); i++) {
            Thread.sleep(2000);
            JSONObject entry = portfolio.getJSONObject(i);
            String ticker = entry.getString("ticker");
            String isin = entry.getString("isin");
            String yahooTicker;
            String yahooSummaryJson;
            String url = String.format("https://query2.finance.yahoo.com/v1/finance/search?q=%s&quotesCount=1&newsCount=0", isin);
            try {
                yahooSummaryJson = HttpUtil.getTickerByIsin(url);
                yahooTicker = (String) new JSONObject(yahooSummaryJson).getJSONArray("quotes").getJSONObject(0).get("symbol");
            } catch (Exception e) {
                log.info(e.getMessage());
                yahooTicker = "-";
            }

            repository.save(new TickerDictionaryEntity(ticker, yahooTicker));
            String line = String.format("(%d, '%s', '%s'),\n", k, ticker, yahooTicker);
            printWriter.print(line);
            log.info("tcs - " + ticker + " yahoo: " + yahooTicker);
            k++;
        }
        printWriter.close();
    }

    public String getTickerFromDictionary(String tcsTicker) {
        return repository.findByTcsTicker(tcsTicker).getYahooTicker();
    }
}
