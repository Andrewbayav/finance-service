package invest.service.dto.representation;

import invest.service.entity.AnalysisEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnalysisDto {
    String ticker;
    String name;
    double recommendationMean;
    double returnOnEquity;
    double priceToBook;
    double enterpriseValue;
    double dividendYield;
    double trailingPE;
    double priceToSalesTrailing12Months;
    double marketCap;

    public AnalysisDto(String ticker,
                       String name,
                       double recommendationMean,
                       double returnOnEquity,
                       double priceToBook,
                       double enterpriseValue,
                       double dividendYield,
                       double trailingPE,
                       double priceToSalesTrailing12Months,
                       double marketCap) {
        this.ticker = ticker;
        this.name = name;
        this.recommendationMean = recommendationMean;
        this.returnOnEquity = returnOnEquity;
        this.priceToBook = priceToBook;
        this.enterpriseValue = enterpriseValue;
        this.dividendYield = dividendYield;
        this.trailingPE = trailingPE;
        this.priceToSalesTrailing12Months = priceToSalesTrailing12Months;
        this.marketCap = marketCap;
    }

    public AnalysisDto(AnalysisEntity entity) {
        this.ticker = entity.getTicker();
        this.name = entity.getName();
        this.recommendationMean = entity.getRecommendationMean();
        this.returnOnEquity = entity.getReturnOnEquity();
        this.priceToBook = entity.getPriceToBook();
        this.enterpriseValue = entity.getEnterpriseValue();
        this.dividendYield = entity.getDividendYield();
        this.trailingPE = entity.getTrailingPE();
        this.priceToSalesTrailing12Months = entity.getPriceToSalesTrailing12Months();
        this.marketCap = entity.getMarketCap();
    }

}
