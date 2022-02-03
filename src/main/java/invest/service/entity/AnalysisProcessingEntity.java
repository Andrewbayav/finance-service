package invest.service.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "analysis_requests")
public class AnalysisProcessingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    UUID uuid;

    @Column(name = "timestamp")
    LocalDateTime timestamp;

    public AnalysisProcessingEntity(UUID uuid) {
        this.uuid = uuid;
        this.timestamp = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
    }
}


