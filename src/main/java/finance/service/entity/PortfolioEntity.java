package finance.service.entity;

import finance.service.dto.YahooFinancialDto;
import finance.service.dto.YahooStatisticsDto;
import finance.service.dto.YahooSummaryDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;

import javax.persistence.*;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "portfolio")
public class PortfolioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "timestamp")
    LocalDateTime timestamp;

    public PortfolioEntity(UUID uuid) {
        this.uuid = uuid;
        this.timestamp = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
    }

}
