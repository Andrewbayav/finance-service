package invest.service.repository.yahoo;

import invest.service.entity.yahoo.YahooFinancialEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YahooFinancialRepository extends CrudRepository<YahooFinancialEntity, Long> {
}
