package finance.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import finance.service.repository.StockRepository;


@Slf4j
@SpringBootApplication
public class FinanceServiceApplication {

	@Autowired
	private StockRepository stockRepository;

	public static void main(String[] args) {
		SpringApplication.run(FinanceServiceApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	private void testHttp(){
//		getYahooStockInfo(apiUrl, tickers, params);
//	}

//	public void getYahooStockInfo(String apiUrl, String tickers, String params) {
//
//		String[] tickersArray = tickers.split(",");
//
//		for (String ticker : tickersArray) {
//			log.info("Send request for ticker: " + ticker);
//			String responseBody = HttpUtil.sendRequest(apiUrl, ticker, params);
//			StockDto stockDto = JsonParserUtil.jsonObjectToStockDto(responseBody);
//			if (stockDto.isStockDtoCorrect()) {
//				Stock stock = new Stock(stockDto);
//				stockRepository.save(stock);
//				log.info("Stock with params: " + stockDto.toString() + " was saved to database");
//			} else {
//				log.warn("Request for " + ticker + " returned stock with zero values.");
//				log.warn("Stock params: " + stockDto.toString());
//			}
//		}
//	}
}
