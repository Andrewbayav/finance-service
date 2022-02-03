package invest.service.repository.yahoo;

import invest.service.entity.yahoo.YahooStatisticsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YahooStatisticsRepository extends CrudRepository<YahooStatisticsEntity, Long> {
}
