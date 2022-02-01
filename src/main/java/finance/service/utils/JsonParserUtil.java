package finance.service.utils;

import finance.service.dto.YahooFinancialDto;
import finance.service.dto.ExchangeDto;
import finance.service.dto.YahooStatisticsDto;
import finance.service.dto.YahooSummaryDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

@Slf4j
public class JsonParserUtil {

    public static ExchangeDto jsonObjectToExchangeDto(String responseBody, String ticker) {
        try {
            JSONObject yahooJsonObject = getYahooJsonObject(responseBody, "price");
            String name = yahooJsonObject.getString("shortName").replaceAll("USD/","");
            double rate = parseDoubleRaw(yahooJsonObject.getJSONObject("regularMarketPrice"));
            return new ExchangeDto(ticker, name, rate);
        } catch (Exception e) {
            log.warn("Got exception with this response: " + responseBody);
            e.printStackTrace();
            return new ExchangeDto(ticker);
        }
    }

    public static YahooStatisticsDto jsonToYahooStatisticsObj(String responseBody, String ticker) {
        try {
            JSONObject yahooJsonObject = getYahooJsonObject(responseBody, "defaultKeyStatistics");

            double enterpriseValue = parseDoubleRaw(yahooJsonObject.getJSONObject("enterpriseValue"));
            double priceToBook = parseDoubleRaw(yahooJsonObject.getJSONObject("priceToBook"));
            double enterpriseToEbitda = parseDoubleRaw(yahooJsonObject.getJSONObject("enterpriseToEbitda"));

            return new YahooStatisticsDto(ticker, enterpriseValue, priceToBook, enterpriseToEbitda);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new YahooStatisticsDto(ticker);
        }
    }

    public static YahooFinancialDto jsonToYahooFinancialObj(String responseBody, String ticker) {
        try {
            JSONObject yahooJsonObject = getYahooJsonObject(responseBody, "financialData");

            double recommendationMean = parseDoubleRaw(yahooJsonObject.getJSONObject("recommendationMean"));
            double returnOnEquity = parseDoubleRaw(yahooJsonObject.getJSONObject("returnOnEquity"));

            return new YahooFinancialDto(ticker, recommendationMean, returnOnEquity);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return new YahooFinancialDto(ticker);
        }
    }

    public static YahooSummaryDto jsonToYahooSummaryObj(String responseBody, String ticker) {
        try {
            JSONObject yahooJsonObject = getYahooJsonObject(responseBody, "summaryDetail");

            double dividendYield = parseDoubleRaw(yahooJsonObject.getJSONObject("dividendYield"));
            double trailingPE = parseDoubleRaw(yahooJsonObject.getJSONObject("trailingPE"));
            double forwardPE = parseDoubleRaw(yahooJsonObject.getJSONObject("forwardPE"));
            double marketCap = parseDoubleRaw(yahooJsonObject.getJSONObject("marketCap"));
            double priceToSalesTrailing12Months = parseDoubleRaw(yahooJsonObject.getJSONObject("priceToSalesTrailing12Months"));

            return new YahooSummaryDto(ticker, dividendYield, trailingPE, forwardPE, marketCap, priceToSalesTrailing12Months);

        } catch (Exception e) {
            log.warn(e.getMessage());
            return new YahooSummaryDto(ticker);
        }
    }

    public static double parseDoubleRaw(JSONObject object) {
        try {
            return Math.round(object.getDouble("raw") * 100.0) / 100.0;
        } catch (Exception e) {
            return 0.0;
        }
    }

    public static JSONObject getYahooJsonObject(String responseBody, String key) throws JSONException {
        return new JSONObject(responseBody)
                .getJSONObject("quoteSummary")
                .getJSONArray("result")
                .getJSONObject(0)
                .getJSONObject(key);
    }
}
