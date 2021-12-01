package finance.service.dto;

import lombok.Data;

@Data
public class YahooFinancialDto {
    private String ticker;
    private double recommendationMean;
    private double returnOnEquity;

    public YahooFinancialDto(String ticker, double recommendationMean, double returnOnEquity) {
        this.ticker = ticker;
        this.recommendationMean = recommendationMean;
        this.returnOnEquity = returnOnEquity;
    }

    public YahooFinancialDto(String ticker) {
        this.ticker = ticker;
        this.recommendationMean = 0.0;
        this.returnOnEquity = 0.0;
    }
}
