package finance.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import finance.service.entity.Stock;

import java.util.List;


public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByTicker(String ticker);

//    @Query("select u from Users u where u.email like '%@gmail.com%'")//если этого мало можно написать
//        //собственный запрос на языке похожем на SQL
//    List<Stock> findWhereEmailIsGmail();

//    @Query(value = "select * from users where name like '%smith%'", nativeQuery = true)
//        //если и этого мало - можно написать запрос на чистом SQL и все это будет работать
//    List<Stock> findWhereNameStartsFromSmith();

}
