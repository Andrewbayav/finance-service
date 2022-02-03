package invest.service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
