package finance.service.api.controller;

import finance.service.api.controller.request.TinkoffPortfolioRequest;
import finance.service.entity.PortfolioEntry;
import finance.service.service.StockService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping(value = "/tinkoff", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class TinkoffController {

    private final StockService stockService;

    @PostMapping("/portfolio")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Запрос информации о портфеле пользователя")
    public ArrayList<PortfolioEntry> requestPortfolio(@RequestBody TinkoffPortfolioRequest tinkoffPortfolioRequest) {
        try {
            return this.stockService.getPortfolio(tinkoffPortfolioRequest.getToken());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
