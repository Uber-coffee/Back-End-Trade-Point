package trade_point.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import trade_point.config.swagger2.SwaggerMethodToDocument;
import trade_point.entity.TradePoint;
import trade_point.payload.TradePointRequest;
import trade_point.service.TradePointService;
import trade_point.util.Views;

import java.util.List;

@Api(value = "This controller allow to add new trade point")
@RestController
public class TradePointController {
    private final TradePointService tradePointService;

    @Autowired
    public TradePointController(TradePointService tradePointService) {
        this.tradePointService = tradePointService;
    }

    @SwaggerMethodToDocument
    @PostMapping("/w/user/trade-points")
//    @PreAuthorize(value = "hasRole('ROLE_MANAGER')")
    @ApiOperation(value = "To create new Trade Point")
    public ResponseEntity<Object> create(@RequestBody TradePointRequest tradePoint) {
        return tradePointService.create(tradePoint);
    }

    @SwaggerMethodToDocument
    @GetMapping("/w/user/trade-points")
    @ApiOperation(value = "To show all Trade Points")
    @JsonView(Views.Compact.class)
    public List<TradePoint> showAllTradePoints(){
        return tradePointService.allTradePointService();
    }

    @SwaggerMethodToDocument
    @GetMapping("/m/trade-points/nearest")
    public ResponseEntity<Object> getNearestPickPoint(@RequestParam Double latitude, @RequestParam Double longitude ) {
        if (latitude == null || longitude == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(tradePointService.getListNearestPickPoint(latitude, longitude), HttpStatus.OK);
    }

    @SwaggerMethodToDocument
    @PostMapping("/w/user/trade-points/del")
    @ApiOperation(value = "Delete trade point")
    public ResponseEntity<Object> delete(@RequestBody Long id) {
        return tradePointService.delete(id);
    }

}
