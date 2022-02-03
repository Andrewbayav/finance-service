package invest.service.repository;

import invest.service.entity.ExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Long> {
}
