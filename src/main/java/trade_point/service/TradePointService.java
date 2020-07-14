package trade_point.service;

import org.apache.commons.math3.util.Precision;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trade_point.data.TradePointDTO;
import trade_point.entity.TradePoint;
import trade_point.payload.TradePointRequest;
import trade_point.repository.TradePointRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class TradePointService {
    private final TradePointRepository tradePointRepository;

    public TradePointService(TradePointRepository tradePointRepository) {
        this.tradePointRepository = tradePointRepository;
    }

    public ResponseEntity<Object> create(TradePointRequest tradePoint) {
        if (tradePointRepository.existsByName(tradePoint.getName())) {
            return new ResponseEntity<>(-1, HttpStatus.FORBIDDEN);
        } else {
            TradePoint point = new TradePoint();

            point.setAddress(tradePoint.getAddress());
            point.setLatitude(tradePoint.getLatitude());
            point.setLongitude(tradePoint.getLongitude());
            point.setName(tradePoint.getName());
            point.setIsActive(true);

            tradePointRepository.save(point);
            return new ResponseEntity<>(point.getId(), HttpStatus.OK);
        }
    }

    public List<TradePoint> allTradePointService () {
        return tradePointRepository.findAll();
    }

    public List <TradePointDTO> getListNearestPickPoint(Double longitude, Double latitude){

        final double DEGREES_IN_ONE_KILOMETER = 0.018074;
        final double RADIUS = 6371.0;
        List <TradePoint> list = tradePointRepository.findByLongitudeBetweenAndLatitudeBetween(
                longitude - DEGREES_IN_ONE_KILOMETER,
                longitude + DEGREES_IN_ONE_KILOMETER,
                latitude - DEGREES_IN_ONE_KILOMETER,
                latitude + DEGREES_IN_ONE_KILOMETER
        );

        List <TradePointDTO> result = new ArrayList<>();
        double distance;
        for (TradePoint p: list) {
            TradePointDTO tmp = new TradePointDTO();
            tmp.setTradePoint(p);

            distance = Math.acos(Math.sin(Math.toRadians(p.getLatitude())) * Math.sin(Math.toRadians(latitude)) +
                    Math.cos(Math.toRadians(p.getLatitude())) * Math.cos(Math.toRadians(latitude)) *
                            Math.cos(Math.toRadians(p.getLongitude() - longitude))) * RADIUS;

            if (distance <= 2.0 && distance >=0) {
                if (distance == 0.000)
                    tmp.setDistance(Precision.round(distance, 3) + 0.001);
                else
                    tmp.setDistance(Precision.round(distance, 3));
                tmp.setTime((int) (tmp.getDistance() * 1000 / 90) + 1);
                result.add(tmp);
            }
        }

        result.sort(Comparator.comparing(TradePointDTO::getDistance));
        return result;
    }

    public ResponseEntity<Object> delete (Long id) {
        if (!tradePointRepository.existsById(id)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
        tradePointRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
