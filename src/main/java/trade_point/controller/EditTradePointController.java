package trade_point.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import trade_point.entity.TradePoint;
import trade_point.entity.User;
import trade_point.payload.EditSellerRequest;
import trade_point.service.EditTradePointService;
import trade_point.config.swagger2.SwaggerMethodToDocument;
import trade_point.util.JsonJodaDateTimeSerializer;
import trade_point.util.Views;

import java.util.ArrayList;
import java.util.List;

@Api(value = "This controller allow to edit trade point")
@RestController
@RequestMapping(value = "/w/user/trade-points/")
public class EditTradePointController {
    private final EditTradePointService editTradePointService;

    @Autowired
    public EditTradePointController(EditTradePointService editTradePointService) {
        this.editTradePointService = editTradePointService;
    }

    @SwaggerMethodToDocument
    @GetMapping("{idTradePoint}/valid")
    @PreAuthorize(value = "hasRole('ROLE_MANAGER') || hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "Return user params")
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @JsonView(Views.Compact.class)
    public ResponseEntity<Object> validateSeller(@RequestBody EditSellerRequest editSellerRequest,
                                                 @PathVariable("idTradePoint") TradePoint tradePoint) {
        return editTradePointService.validSeller(editSellerRequest, tradePoint);
    }

    @SwaggerMethodToDocument
    @PostMapping("{idTradePoint}/add")
    @PreAuthorize(value = "hasRole('ROLE_MANAGER') || hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "To add a seller to another trade point")
    public ResponseEntity<Object> addSeller(@RequestParam Long idSeller,
                                            @PathVariable("idTradePoint") TradePoint tradePoint) {
        return editTradePointService.addSeller(idSeller, tradePoint);
    }

    @SwaggerMethodToDocument
    @GetMapping("{idTradePoint}")
    @ApiOperation(value = "To show seller to chosen trade point")
    @JsonSerialize(using = JsonJodaDateTimeSerializer.class)
    @JsonView(Views.Compact.class)
    public List<User> showAllSellersOnThisTradePoint(@PathVariable("idTradePoint") TradePoint tradePoint) {
        return new ArrayList<>(tradePoint.getUsers());
    }


    @SwaggerMethodToDocument
    @PostMapping("del/{idTradePoint}")
    @PreAuthorize(value = "hasRole('ROLE_MANAGER') || hasRole('ROLE_ADMIN')")
    @ApiOperation(value = "To delete seller of chosen trade point")
    public ResponseEntity<Object> deleteSellerTradePoint(@RequestParam Long id,
                                                         @PathVariable("idTradePoint") TradePoint tradePoint) {
        return editTradePointService.deleteSeller(id, tradePoint);
    }


}

