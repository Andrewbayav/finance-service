package invest.service.repository;

import invest.service.entity.AnalysisEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface AnalysisRepository extends CrudRepository<AnalysisEntity, Long> {
    List<AnalysisEntity> findAll();
}
