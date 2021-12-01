package finance.service.dto;

import lombok.Data;

@Data
public class YahooSummaryDto {

    private String ticker;
    private double dividendYield;
    private double trailingPE;
    private double forwardPE;
    private double marketCap;
    private double priceToSalesTrailing12Months;

    public YahooSummaryDto(String ticker,
                           double dividendYield,
                           double trailingPE,
                           double forwardPE,
                           double marketCap,
                           double priceToSalesTrailing12Months) {
        this.ticker = ticker;
        this.dividendYield = dividendYield;
        this.trailingPE = trailingPE;
        this.forwardPE = forwardPE;
        this.marketCap = marketCap;
        this.priceToSalesTrailing12Months = priceToSalesTrailing12Months;
    }

    public YahooSummaryDto(String ticker) {
        this.ticker = ticker;
        this.dividendYield = 0.0;
        this.trailingPE = 0.0;
        this.forwardPE = 0.0;
        this.marketCap = 0.0;
        this.priceToSalesTrailing12Months = 0.0;
    }
}
