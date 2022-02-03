package invest.service.repository;

import invest.service.entity.YahooSummaryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YahooSummaryRepository extends CrudRepository<YahooSummaryEntity, Long> {
}
