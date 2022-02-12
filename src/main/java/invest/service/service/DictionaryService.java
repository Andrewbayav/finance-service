package invest.service.service;

import invest.service.dto.representation.DictionaryDto;
import invest.service.repository.TickerDictionaryRepository;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import invest.service.entity.TickerDictionaryEntity;
import invest.service.utils.HttpUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@Service
@Slf4j
public class DictionaryService {

    @Value("${tinkoff.api.url}")
    private String tinkoffApiUrl;

    @Value("${tinkoff.api.url.market.stocks}")
    private String marketStocks;

    @Value("${yahoo.api.isin.url}")
    private String isinUrl;

    @Value("${filepath}")
    private String filePath;


    private final TickerDictionaryRepository dictionaryRepository;
    private final TcsService tcsService;

    @Autowired
    public DictionaryService(TickerDictionaryRepository dictionaryRepository, TcsService tcsService) {
        this.dictionaryRepository = dictionaryRepository;
        this.tcsService = tcsService;
    }


    public List<DictionaryDto> getDictionary() {
        return this.dictionaryRepository.findAll().stream()
                .map(x -> new DictionaryDto(x.getName(), x.getTcsTicker(), x.getYahooTicker()))
                .collect(Collectors.toList());
    }


//    TODO: Будем ли обновлять базу тикеров по ISIN?
//    public List<TickerDictionaryEntity> getMarketStocks(String token) throws JSONException, IOException, InterruptedException {
//        String jsonData = HttpUtil.sendTinkoffPortfolioRequest(tinkoffApiUrl + marketStocks, token);
//        List<TickerDictionaryEntity> list = new ArrayList<>();
//        File file = new File(filePath);
//        FileWriter fileWriter = new FileWriter(file);;
//        PrintWriter printWriter = new PrintWriter(fileWriter);
//        int k = 1;
//        JSONArray portfolio = new JSONObject(jsonData).getJSONObject("payload").getJSONArray("instruments");
//        for (int i = 0; i < portfolio.length(); i++) {
//            Thread.sleep(2000);
//            JSONObject entry = portfolio.getJSONObject(i);
//            String ticker = entry.getString("ticker");
//            String isin = entry.getString("isin");
//            String name = entry.getString("name");
//            if (name.contains("'")) name = name.replaceAll("'","''");
//            String yahooTicker;
//            String yahooSummaryJson;
//            String url = String.format(isinUrl, isin);
//            try {
//                yahooSummaryJson = HttpUtil.getTickerByIsin(url);
//                yahooTicker = (String) new JSONObject(yahooSummaryJson).getJSONArray("quotes").getJSONObject(0).get("symbol");
//            } catch (Exception e) {
//                log.info(e.getMessage());
//                yahooTicker = "-";
//            }
//
//            list.add(new TickerDictionaryEntity(name, ticker, yahooTicker));
//            String line = String.format("(%d, '%s' ,'%s', '%s'),\n", k, name, ticker, yahooTicker);
//            printWriter.print(line);
//            log.info("tcs - " + ticker + " yahoo: " + yahooTicker);
//            k++;
//        }
//        printWriter.close();
//        return list;
//    }

}
