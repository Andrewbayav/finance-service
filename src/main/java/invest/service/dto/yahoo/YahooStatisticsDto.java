package invest.service.dto.yahoo;

import lombok.Data;

@Data
public class YahooStatisticsDto {

    private String ticker;
    private double priceToBook;
    private double enterpriseValue;
    private double enterpriseToEbitda;

    public YahooStatisticsDto(String ticker,
                              double enterpriseValue,
                              double priceToBook,
                              double enterpriseToEbitda) {
        this.ticker = ticker;
        this.enterpriseValue = enterpriseValue;
        this.priceToBook = priceToBook;
        this.enterpriseToEbitda = enterpriseToEbitda;
    }

    public YahooStatisticsDto(String ticker) {
        this.ticker = ticker;
        this.enterpriseValue = 0.0;
        this.priceToBook = 0.0;
        this.enterpriseToEbitda = 0.0;
    }
}
