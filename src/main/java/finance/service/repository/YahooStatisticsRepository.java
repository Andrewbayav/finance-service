package finance.service.repository;

import finance.service.entity.YahooStatisticsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YahooStatisticsRepository extends CrudRepository<YahooStatisticsEntity, Long> {
}
