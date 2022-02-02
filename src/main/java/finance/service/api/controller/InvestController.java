package finance.service.api.controller;

import finance.service.api.controller.request.PortfolioRequest;
import finance.service.dto.OverviewDto;
import finance.service.service.StockService;
import finance.service.service.TickerDictionaryService;
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
    private final TickerDictionaryService dictionaryService;

    @Autowired
    public InvestController(StockService stockService, TickerDictionaryService dictionaryService) {
        this.stockService = stockService;
        this.dictionaryService = dictionaryService;
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

    // TODO: определиться, будем как то обновлять базу тикеров по ISIN?
    // Yahoo ограничивает кол-во запросов - 2000 в час с одного IP.
    @PostMapping("/stocks-dictionary")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Для получения справочника тикеров по ISIN")
    public List<OverviewDto> dictionary(@RequestBody PortfolioRequest request) {
        try {
            dictionaryService.getMarketStocks(request.getToken());
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
