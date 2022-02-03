package invest.service.repository;

import invest.service.entity.TcsTickerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TcsRepository extends CrudRepository<TcsTickerEntity, Long> {
}
