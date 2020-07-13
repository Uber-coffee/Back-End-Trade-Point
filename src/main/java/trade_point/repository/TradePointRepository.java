package trade_point.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import trade_point.entity.TradePoint;

import java.util.List;

@Repository
public interface TradePointRepository extends JpaRepository<TradePoint, Long> {
    boolean existsByName(String name);
    boolean findByAddress(String address);
    boolean existsByAddress(String address);

    List<TradePoint> findByLongitudeBetweenAndLatitudeBetween(
            double leftLongitude,
            double rightLongitude,
            double leftLatitude,
            double rightLatitude);
}
