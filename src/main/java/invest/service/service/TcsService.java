package invest.service.service;

import invest.service.dto.TcsTickerDto;
import invest.service.entity.TickerDictionaryEntity;
import invest.service.utils.HttpUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/* TODO: 1. Реализовать логику сохранения данных о ценных бумагах в БД

 * */


@Slf4j
@Service
@RequiredArgsConstructor
public class TcsService {

    @Value("${tinkoff.api.url}")
    private String tinkoffApiUrl;

    @Value("${tinkoff.api.url.portfolio}")
    private String portfolioEndpoint;

    @Value("${tinkoff.api.url.market.stocks}")
    private String marketStocks;

    @Value("${filepath}")
    private String filePath;

    public ArrayList<TcsTickerDto> getPortfolio(String token) throws JSONException {
        String jsonData = HttpUtil.sendTinkoffPortfolioRequest(tinkoffApiUrl + portfolioEndpoint, token);
        ArrayList<TcsTickerDto> list = new ArrayList<>();

        JSONArray portfolio = new JSONObject(jsonData).getJSONObject("payload").getJSONArray("positions");
        for (int i = 0; i < portfolio.length(); i++) {
            JSONObject entry = portfolio.getJSONObject(i);
            String instrumentType = entry.getString("instrumentType");

            // На этом этапе мы обрабатывем только акции
            if (!instrumentType.equals("Stock")) continue;
            list.add(new TcsTickerDto(
                    entry.getString("name"),
                    entry.getString("ticker"),
                    instrumentType,
                    entry.getJSONObject("averagePositionPrice").getString("currency"),
                    entry.getInt("balance"),
                    entry.getJSONObject("expectedYield").getDouble("value"),
                    entry.getJSONObject("averagePositionPrice").getDouble("value")
            ));
        }
        return list;
    }

    //TODO: Причесать, чтобы стало красиво (в последнюю очередь).
    public List<TickerDictionaryEntity> getMarketStocks(String token) throws JSONException, IOException, InterruptedException {
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

            list.add(new TickerDictionaryEntity(ticker, yahooTicker));
            String line = String.format("(%d, '%s', '%s'),\n", k, ticker, yahooTicker);
            printWriter.print(line);
            log.info("tcs - " + ticker + " yahoo: " + yahooTicker);
            k++;
        }
        printWriter.close();
        return list;
    }
}

