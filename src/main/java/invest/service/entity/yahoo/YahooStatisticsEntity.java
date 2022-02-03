package invest.service.entity.yahoo;


import invest.service.dto.yahoo.YahooStatisticsDto;
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
@Table(name = "yahoo_statistics")
public class YahooStatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "priceToBook")
    private double priceToBook;

    @Column(name = "enterpriseValue")
    private double enterpriseValue;

    @Column(name = "enterpriseToEbitda")
    private double enterpriseToEbitda;

    public YahooStatisticsEntity(UUID uuid, YahooStatisticsDto dto) {
        this.uuid = uuid;
        this.ticker = dto.getTicker();
        this.priceToBook = dto.getPriceToBook();
        this.enterpriseValue = dto.getEnterpriseValue();
        this.enterpriseToEbitda = dto.getEnterpriseToEbitda();
        log.info("New YahooStatisticsEntity instance: " + this);
    }

}
