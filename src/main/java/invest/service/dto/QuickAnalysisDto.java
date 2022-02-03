package invest.service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QuickAnalysisDto {
    String ticker;
    double recommendationMean;
    double returnOnEquity;
    double priceToBook;
    double enterpriseValue;
    double dividendYield;
    double trailingPE;
    double priceToSalesTrailing12Months;
    double marketCap;

    public QuickAnalysisDto(String ticker,
                            double recommendationMean,
                            double returnOnEquity,
                            double priceToBook,
                            double enterpriseValue,
                            double dividendYield,
                            double trailingPE,
                            double priceToSalesTrailing12Months,
                            double marketCap) {
        this.ticker = ticker;
        this.recommendationMean = recommendationMean;
        this.returnOnEquity = returnOnEquity;
        this.priceToBook = priceToBook;
        this.enterpriseValue = enterpriseValue;
        this.dividendYield = dividendYield;
        this.trailingPE = trailingPE;
        this.priceToSalesTrailing12Months = priceToSalesTrailing12Months;
        this.marketCap = marketCap;
    }
}
