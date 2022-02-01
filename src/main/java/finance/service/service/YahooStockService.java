package finance.service.service;

import finance.service.dto.ExchangeDto;
import finance.service.dto.YahooFinancialDto;
import finance.service.dto.YahooStatisticsDto;
import finance.service.dto.YahooSummaryDto;
import finance.service.utils.HttpUtil;
import finance.service.utils.JsonParserUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import  java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class YahooStockService {

    @Value("${yahoo.api.url}")
    private String yahooApiUrl;

    @Value("${yahoo.params.price}")
    private String yahooPrice;

    @Value("${yahoo.params.statistics}")
    private String yahooStatistics;

    @Value("${yahoo.params.summaryDetail}")
    private String yahooSummaryDetail;

    @Value("${yahoo.params.financialData}")
    private String yahooFinancialData;

    @Value("${yahoo.exchange.tickers}")
    private String exchangeTickers;

    // TODO: параметризация методов?

    public List<YahooFinancialDto> getFinancialDtoList(String tickers) {
        Function<String, YahooFinancialDto> function = ticker -> {
            log.info("Send financial request for ticker: " + ticker);
            String yahooFinancialJson = HttpUtil.sendYahooTickerRequest(yahooApiUrl, ticker, yahooFinancialData);
            return JsonParserUtil.jsonToYahooFinancialObj(yahooFinancialJson, ticker);
        };
        return Stream.of(tickers.trim().split(" ")).map(t -> function.apply(t)).collect(Collectors.toList());
    }

    public List<YahooStatisticsDto> getStatisticsDtoList(String tickers) {
        Function<String, YahooStatisticsDto> function = ticker -> {
            log.info("Send statistics request for ticker: " + ticker);
            String yahooStatisticsJson = HttpUtil.sendYahooTickerRequest(yahooApiUrl, ticker, yahooStatistics);
            return JsonParserUtil.jsonToYahooStatisticsObj(yahooStatisticsJson, ticker);
        };
        return Stream.of(tickers.trim().split(" ")).map(t -> function.apply(t)).collect(Collectors.toList());
    }

    public List<YahooSummaryDto> getSummaryDtoList(String tickers) {
        Function<String, YahooSummaryDto> function = ticker -> {
            log.info("Send summary request for ticker: " + ticker);
            String yahooSummaryJson = HttpUtil.sendYahooTickerRequest(yahooApiUrl, ticker, yahooSummaryDetail);
            return JsonParserUtil.jsonToYahooSummaryObj(yahooSummaryJson, ticker);
        };
        return Stream.of(tickers.trim().split(" ")).map(t -> function.apply(t)).collect(Collectors.toList());
    }

    public List<ExchangeDto> getExchangeDtoList() {
        Function<String, ExchangeDto> function = ticker -> {
            log.info("Send summary request for ticker: " + ticker);
            String yahooSummaryJson = HttpUtil.sendYahooTickerRequest(yahooApiUrl, ticker, yahooPrice);
            return JsonParserUtil.jsonObjectToExchangeDto(yahooSummaryJson, ticker);
        };
        return Stream.of(exchangeTickers.trim().split(" ")).map(t -> function.apply(t)).collect(Collectors.toList());
    }
}
