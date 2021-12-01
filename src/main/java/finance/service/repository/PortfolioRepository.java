package finance.service.repository;

import finance.service.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {

    PortfolioEntity findFirstByOrderByTimestampDesc();

//    @Query(value = "select uuid, timestamp from portfolio order by timestamp desc limit 1")
//    PortfolioEntity getLatest();
}
