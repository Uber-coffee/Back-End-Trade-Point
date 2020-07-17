package trade_point.service;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import trade_point.entity.Role;
import trade_point.payload.EditSellerRequest;
import trade_point.entity.TradePoint;
import trade_point.entity.User;
import trade_point.exception.UserNotFoundException;
import trade_point.repository.TradePointRepository;
import trade_point.repository.UserRepository;

@Service
public class EditTradePointService {
    private static final ModelMapper mapper = new ModelMapper();

    private final Logger log = LoggerFactory.getLogger(EditTradePointService.class);

    private final UserRepository userRepository;

    private final TradePointRepository tradePointRepository;

    EditTradePointService(
            UserRepository userRepository,
            TradePointRepository tradePointRepository
    ) {
        this.tradePointRepository = tradePointRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<Object> validSeller(String emailSeller, TradePoint tradePoint) {
        if (!userRepository.existsByEmail(emailSeller)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        User user = userRepository.findByEmail(emailSeller);

        if (!user.getRoles().contains(Role.ROLE_SELLER)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity<Object> addSeller(Long idSeller, TradePoint tradePoint) {
        if (!userRepository.existsById(idSeller)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        User user = userRepository.findById(idSeller).get();

        if (!user.getRoles().contains(Role.ROLE_SELLER)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        user.getTradePoint().add(tradePoint);
        tradePoint.getUsers().add(user);

        userRepository.save(user);
        tradePointRepository.save(tradePoint);

        log.debug("Seller ({}) add to trade point ({})", user.getFirstName(), tradePoint.getName());

        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }

    public ResponseEntity<Object> deleteSeller(Long id, TradePoint tradePoint) {
        if (!userRepository.existsById(id)) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }

        User user = userRepository.findById(id).get();

        if (!user.getRoles().contains(Role.ROLE_SELLER)) {
            return new ResponseEntity<>("-1", HttpStatus.FORBIDDEN);
        }

        user.getTradePoint().remove(tradePoint);
        tradePoint.getUsers().remove(user);

        userRepository.save(user);
        tradePointRepository.save(tradePoint);

        log.debug("Seller ({}) delete of trade point ({})", user.getFirstName(), tradePoint.getName());

        return new ResponseEntity<>(user.getId(), HttpStatus.OK);
    }
}
