package finance.service.dto;

import lombok.Data;

@Data
public class YahooStockStatisticsDto {

    private double priceToBook;
    private double forwardPE;
    private double enterpriseValue;
    private double enterpriseToEbitda;

    public YahooStockStatisticsDto(double enterpriseValue, double forwardPE, double priceToBook, double enterpriseToEbitda) {
        this.enterpriseValue = enterpriseValue;
        this.forwardPE = forwardPE;
        this.priceToBook = priceToBook;
        this.enterpriseToEbitda = enterpriseToEbitda;
    }

    public YahooStockStatisticsDto() {
        this.enterpriseValue = 0.0;
        this.forwardPE = 0.0;
        this.priceToBook = 0.0;
        this.enterpriseToEbitda = 0.0;
    }
}
