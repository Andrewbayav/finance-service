package invest.service.entity;

import invest.service.dto.representation.AnalysisDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "market_analytics")
public class AnalysisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    UUID uuid;
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

    public AnalysisEntity(AnalysisDto dto, UUID uuid) {
        this.uuid = uuid;
        this.ticker = dto.getTicker();
        this.name = dto.getName();
        this.recommendationMean = dto.getRecommendationMean();
        this.returnOnEquity = dto.getReturnOnEquity();
        this.priceToBook = dto.getPriceToBook();
        this.enterpriseValue = dto.getEnterpriseValue();
        this.dividendYield = dto.getDividendYield();
        this.trailingPE = dto.getTrailingPE();
        this.priceToSalesTrailing12Months = dto.getPriceToSalesTrailing12Months();
        this.marketCap = dto.getMarketCap();
    }
}
