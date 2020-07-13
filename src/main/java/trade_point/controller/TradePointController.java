package trade_point.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import trade_point.config.swagger2.SwaggerMethodToDocument;
import trade_point.entity.TradePoint;
import trade_point.payload.TradePointRequest;
import trade_point.service.TradePointService;
import trade_point.util.Views;

import java.util.List;

@Api(value = "This controller allow to add new trade point")
@RestController
@RequestMapping(value = "/w/user/manager/")
public class TradePointController {
    private final TradePointService tradePointService;

    @Autowired
    public TradePointController(TradePointService tradePointService) {
        this.tradePointService = tradePointService;
    }

    @SwaggerMethodToDocument
    @PostMapping("/")
//    @PreAuthorize(value = "hasRole('ROLE_MANAGER')")
    @ApiOperation(value = "To create new Trade Point")
    public boolean create(@RequestBody TradePointRequest tradePoint) {
        return tradePointService.create(tradePoint) != null;
    }

    @SwaggerMethodToDocument
    @GetMapping("/trade-points")
    @ApiOperation(value = "To show all Trade Points")
    @JsonView(Views.Compact.class)
    public List<TradePoint> showAllTradePoints(){
        return tradePointService.allTradePointService();
    }
}
