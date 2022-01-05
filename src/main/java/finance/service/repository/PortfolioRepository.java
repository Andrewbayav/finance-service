package finance.service.repository;

import finance.service.entity.PortfolioEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends CrudRepository<PortfolioEntity, Long> {
}
