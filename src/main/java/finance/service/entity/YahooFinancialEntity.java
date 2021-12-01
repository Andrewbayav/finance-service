package finance.service.entity;

import finance.service.dto.YahooFinancialDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "yahoo_financial")
public class YahooFinancialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "recommendationMean")
    private double recommendationMean;

    @Column(name = "returnOnEquity")
    private double returnOnEquity;

    public YahooFinancialEntity(UUID uuid, YahooFinancialDto dto) {
        this.uuid = uuid;
        this.ticker = dto.getTicker();
        this.recommendationMean = dto.getRecommendationMean();
        this.returnOnEquity = dto.getReturnOnEquity();
        log.info("New YahooFinancialEntity instance: " + this);
    }


}
