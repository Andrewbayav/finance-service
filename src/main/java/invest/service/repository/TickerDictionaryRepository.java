package invest.service.repository;

import invest.service.entity.TickerDictionaryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TickerDictionaryRepository extends CrudRepository<TickerDictionaryEntity, Long> {
     TickerDictionaryEntity findByTcsTicker(String ticker);

     List<TickerDictionaryEntity> findAll();

}
