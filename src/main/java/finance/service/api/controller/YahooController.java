package finance.service.api.controller;

import finance.service.api.controller.request.YahooRequest;
import finance.service.service.StockService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/yahoo", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class YahooController {

    private final StockService stockService;

    @GetMapping("/ticker")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запрос информации от Yahoo Finance Api по тикеру")
    public void requestInfo(@RequestBody YahooRequest yahooRequest) {
        try {
            this.stockService.getYahooStockInfo(yahooRequest.getTicker());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


