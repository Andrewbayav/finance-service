package invest.service.repository.yahoo;

import invest.service.entity.yahoo.YahooSummaryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YahooSummaryRepository extends CrudRepository<YahooSummaryEntity, Long> {
}
