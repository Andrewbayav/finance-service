package invest.service.entity;

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

@Data
@Entity
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "ticker_dictionary")
public class TickerDictionaryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tcs_ticker")
    private String tcsTicker;

    @Column(name = "yahoo_ticker")
    private String yahooTicker;

    public TickerDictionaryEntity(String tcsTicker, String yahooTicker) {
        this.tcsTicker = tcsTicker;
        this.yahooTicker = yahooTicker;
    }
}
