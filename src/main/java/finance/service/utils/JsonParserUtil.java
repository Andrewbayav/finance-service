package finance.service.utils;

import finance.service.dto.YahooStockDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

@Slf4j
public class JsonParserUtil {

    public static YahooStockDto jsonObjectToStockDto(String responseBody){

        JSONObject stockJsonInfo = null;
        String stockTicker = "";
        String stockName = "";
        double stockPrice = 0.0;

        // TODO: исправить логику обработки ошибок
        try {
            JSONObject object = new JSONObject(responseBody);
            JSONObject stockJsonObj = (JSONObject) object.get("quoteSummary");
            JSONObject stockInfoArrayObj = (JSONObject) stockJsonObj.getJSONArray("result").get(0);
            stockJsonInfo = (JSONObject) stockInfoArrayObj.get("price");
            JSONObject jsonStockPrice = (JSONObject) stockJsonInfo.get("regularMarketPrice");

            stockTicker =  stockJsonInfo.getString("symbol");
            stockName =  stockJsonInfo.getString("shortName");
            stockPrice = Double.parseDouble(jsonStockPrice.getString("raw"));

        } catch (JSONException e) {
            log.warn("Got exception with this response: " + responseBody);
            e.printStackTrace();
        }
        return new YahooStockDto(stockTicker, stockName, stockPrice);
    }

}
