package invest.service.repository.yahoo;

import invest.service.entity.yahoo.ExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Long> {
}
