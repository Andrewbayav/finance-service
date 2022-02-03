package invest.service.repository;

import invest.service.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {

    PortfolioEntity findFirstByOrderByTimestampDesc();

//    @Query(value = "select uuid, timestamp from portfolio order by timestamp desc limit 1")
//    PortfolioEntity getLatest();
}
