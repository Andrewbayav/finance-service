package invest.service.api.controller;

import invest.service.api.controller.request.PortfolioRequest;
import invest.service.dto.representation.OverviewDto;
import invest.service.dto.representation.QuickAnalysisDto;
import invest.service.service.StockService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/tinkoff", produces = {MediaType.APPLICATION_JSON_VALUE})
public class InvestController {

    private final StockService stockService;

    @Autowired
    public InvestController(StockService stockService) {
        this.stockService = stockService;
    }

    // TODO: добавить ControllerAdvice
    @PostMapping("/portfolio")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запрос информации о портфеле пользователя")
    public List<OverviewDto> requestPortfolio(@RequestBody PortfolioRequest request) {
        try {
            return request.getRefresh() ? stockService.getNewData(request.getToken()) : stockService.getLatestAvailableData(request.getToken());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @GetMapping("/market-analyzer")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Для получения быстрого анализа по тикеру")
    public List<QuickAnalysisDto> dictionary(@RequestParam String tickers) {
        try {
            return stockService.getQuickAnalysis(tickers);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<QuickAnalysisDto>();
        }
    }

    @GetMapping("/all-stocks-analyzer")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Выгружаем информацио о всем рынке акций")
    public HttpStatus allMarketInfo() {
        try {
            stockService.getAllMarketStocksInfo();
            return HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }



    @PostMapping("/stocks-dictionary")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Для получения справочника тикеров по ISIN")
    public List<OverviewDto> dictionary(@RequestBody PortfolioRequest request) {
        // TODO: определиться, будем как то обновлять базу тикеров по ISIN?
        // Yahoo ограничивает кол-во запросов - 2000 в час с одного IP.
        try {
            stockService.getMarketStocks(request.getToken());
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        //    @Autowired
//    KafkaTemplate<String, String> kafkaTemplate;
//
//    static final String TOPIC = "gfg";
//
//    // Implementing a GET method
//    @GetMapping("/publish")
//    public String publish_message()
//    {
//        kafkaTemplate.send(TOPIC, "message");
//        return "Message Published on Kafka !";
//    }
    }
}
