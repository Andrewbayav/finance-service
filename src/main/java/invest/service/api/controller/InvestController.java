package invest.service.api.controller;

import invest.service.api.controller.request.PortfolioRequest;
import invest.service.dto.representation.OverviewDto;
import invest.service.dto.representation.AnalysisDto;
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

    @GetMapping("/ticker")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Для получения быстрого анализа по тикеру")
    public List<AnalysisDto> realTimeCheck(@RequestParam String tickers) {
        try {
            return stockService.getQuickAnalysis(tickers);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
