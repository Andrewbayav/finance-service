package finance.service.entity;

import finance.service.dto.TcsTickerDto;
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
@Table(name = "tcs")
public class TcsTickerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "ticker")
    private String ticker;

    @Column(name = "instrumentType")
    private String instrumentType;

    @Column(name = "currency")
    private String currency;

    @Column(name = "balance")
    private int balance;

    @Column(name = "expectedYield")
    private double expectedYield;

    @Column(name = "averagePositionPrice")
    private double averagePositionPrice;

    public TcsTickerEntity(UUID uuid, TcsTickerDto dto) {
        this.uuid = uuid;
        this.name = dto.getName();
        this.ticker = dto.getTicker();
        this.instrumentType = dto.getInstrumentType();
        this.currency = dto.getCurrency();
        this.balance = dto.getBalance();
        this.expectedYield = dto.getExpectedYield();
        this.averagePositionPrice = dto.getAveragePositionPrice();
        log.info("New instance: " + this);
    }
}
