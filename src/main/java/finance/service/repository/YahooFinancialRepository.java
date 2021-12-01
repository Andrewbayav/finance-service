package finance.service.repository;

import finance.service.entity.YahooFinancialEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YahooFinancialRepository extends CrudRepository<YahooFinancialEntity, Long> {
}
