package finance.service.repository;

import finance.service.entity.ExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<ExchangeEntity, Long> {
}
