package finance.service.repository;

import finance.service.entity.YahooStatisticsEntity;
import finance.service.entity.YahooSummaryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YahooSummaryRepository extends CrudRepository<YahooSummaryEntity, Long> {
}
