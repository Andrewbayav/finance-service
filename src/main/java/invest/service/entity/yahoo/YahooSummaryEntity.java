package invest.service.entity.yahoo;

import invest.service.dto.yahoo.YahooSummaryDto;
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
@Table(name = "yahoo_summary")
public class YahooSummaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "dividendYield")
    private double dividendYield;

    @Column(name = "trailingPE")
    private double trailingPE;

    @Column(name = "forwardPE")
    private double forwardPE;

    @Column(name = "marketCap")
    private double marketCap;

    @Column(name = "priceToSalesTrailing12Months")
    private double priceToSalesTrailing12Months;

    public YahooSummaryEntity(UUID uuid, YahooSummaryDto dto) {
        this.uuid = uuid;
        this.ticker = dto.getTicker();
        this.dividendYield = dto.getDividendYield();
        this.trailingPE = dto.getTrailingPE();
        this.forwardPE = dto.getForwardPE();
        this.marketCap = dto.getMarketCap();
        this.priceToSalesTrailing12Months = dto.getPriceToSalesTrailing12Months();
        log.info("New YahooSummaryEntity instance: " + this);
    }

}
