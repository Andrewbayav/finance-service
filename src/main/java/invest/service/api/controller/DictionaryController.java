package invest.service.api.controller;


import invest.service.api.controller.request.PortfolioRequest;
import invest.service.dto.representation.DictionaryDto;
import invest.service.dto.representation.OverviewDto;
import invest.service.service.DictionaryService;
import invest.service.service.StockService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/dictionary", produces = {MediaType.APPLICATION_JSON_VALUE})
public class DictionaryController {

    private final StockService stockService;
    private final DictionaryService dictionaryService;

    @Autowired
    public DictionaryController(StockService stockService, DictionaryService dictionaryService) {
        this.stockService = stockService;
        this.dictionaryService = dictionaryService;
    }


    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Получение справочника тикеров")
    public List<DictionaryDto> dictionary() {
        try {
            return dictionaryService.getDictionary();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


//    TODO: Будем ли обновлять базу тикеров по ISIN?
//    @PostMapping("/refresh")
//    @ResponseStatus(HttpStatus.OK)
//    @ApiOperation("Обновление справочника тикеров")
//    public List<OverviewDto> refreshDictionary(@RequestBody PortfolioRequest request) {
//        // Yahoo ограничивает кол-во запросов - 2000 в час с одного IP.
//        try {
//            dictionaryService.getMarketStocks(request.getToken());
//            return new ArrayList<>();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ArrayList<>();
//        }
//    }
}
