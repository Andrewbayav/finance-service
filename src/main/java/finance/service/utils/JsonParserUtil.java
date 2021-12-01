package finance.service.utils;

import finance.service.dto.YahooPriceDto;
import finance.service.dto.YahooStockFinancialDto;
import finance.service.dto.YahooStockStatisticsDto;
import finance.service.dto.YahooStockSummaryDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

@Slf4j
public class JsonParserUtil {

    public static YahooPriceDto jsonObjectToStockDto(String responseBody) {

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

            stockTicker = stockJsonInfo.getString("symbol");
            stockName = stockJsonInfo.getString("shortName");
            stockPrice = Double.parseDouble(jsonStockPrice.getString("raw"));

        } catch (JSONException e) {
            log.warn("Got exception with this response: " + responseBody);
            e.printStackTrace();
        }
        return new YahooPriceDto(stockTicker, stockName, stockPrice);
    }

    public static YahooStockStatisticsDto jsonToYahooStatisticsObj(String ticker, String responseBody) {
        try {
            JSONObject responseJsonInfo = new JSONObject(responseBody)
                    .getJSONObject("quoteSummary")
                    .getJSONArray("result")
                    .getJSONObject(0)
                    .getJSONObject("defaultKeyStatistics");

            double enterpriseValue = parseDoubleStatistics(responseJsonInfo.getJSONObject("enterpriseValue"), "raw");
            double forwardPE = parseDoubleStatistics(responseJsonInfo.getJSONObject("forwardPE"), "raw");
            double priceToBook = parseDoubleStatistics(responseJsonInfo.getJSONObject("priceToBook"), "raw");
            double enterpriseToEbitda = parseDoubleStatistics(responseJsonInfo.getJSONObject("enterpriseToEbitda"), "raw");

            return new YahooStockStatisticsDto(enterpriseValue, forwardPE, priceToBook, enterpriseToEbitda);

        } catch (JSONException e) {
            e.printStackTrace();
            return new YahooStockStatisticsDto();
        }
    }

    public static YahooStockFinancialDto jsonToYahooFinancialObj(String ticker, String responseBody) {

//        financialData
//                quoteSummary
//                    result (Array)
//                        financialData
//                            recommendationMean +
//                            returnOnEquity +
        try {
            JSONObject responseJsonInfo = new JSONObject(responseBody)
                    .getJSONObject("quoteSummary")
                    .getJSONArray("result")
                    .getJSONObject(0)
                    .getJSONObject("defaultKeyStatistics");

            double enterpriseValue = parseDoubleStatistics(responseJsonInfo.getJSONObject("enterpriseValue"), "raw");
            double forwardPE = parseDoubleStatistics(responseJsonInfo.getJSONObject("forwardPE"), "raw");
            double priceToBook = parseDoubleStatistics(responseJsonInfo.getJSONObject("priceToBook"), "raw");
            double enterpriseToEbitda = parseDoubleStatistics(responseJsonInfo.getJSONObject("enterpriseToEbitda"), "raw");

            return new YahooStockStatisticsDto(enterpriseValue, forwardPE, priceToBook, enterpriseToEbitda);

        } catch (JSONException e) {
            e.printStackTrace();
            return new YahooStockStatisticsDto();
        }
    }

    public static YahooStockSummaryDto jsonToYahooSummaryObj(String ticker, String responseBody) {

//        summaryDetail
//                quoteSummary
//                  result (Array)
//                      summaryDetail
//                          dividendYield +
//                          trailingPE +
//                          forwardPE +
//                          marketCap +
//                          priceToSalesTrailing12Months +

        try {
            JSONObject responseJsonInfo = new JSONObject(responseBody)
                    .getJSONObject("quoteSummary")
                    .getJSONArray("result")
                    .getJSONObject(0)
                    .getJSONObject("defaultKeyStatistics");

            double cap = parseDoubleStatistics(responseJsonInfo.getJSONObject("enterpriseValue"), "raw");
            double priceToBook = parseDoubleStatistics(responseJsonInfo.getJSONObject("priceToBook"), "raw");
            double forwardPE = parseDoubleStatistics(responseJsonInfo.getJSONObject("forwardPE"), "raw");
            double enterpriseValue = parseDoubleStatistics(responseJsonInfo.getJSONObject("enterpriseValue"), "raw");
            double enterpriseToRevenue = parseDoubleStatistics(responseJsonInfo.getJSONObject("enterpriseToRevenue"), "raw");
            double enterpriseToEbitda = parseDoubleStatistics(responseJsonInfo.getJSONObject("enterpriseToEbitda"), "raw");

            return new YahooStockSummaryDto(ticker, cap, priceToBook, forwardPE, enterpriseValue, enterpriseToRevenue, enterpriseToEbitda);

        } catch (JSONException e) {
            e.printStackTrace();
            return new YahooStockSummaryDto(ticker);
        }
    }

    public static double parseDoubleStatistics(JSONObject object, String key) {
        try {
            return object.getDouble(key);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
