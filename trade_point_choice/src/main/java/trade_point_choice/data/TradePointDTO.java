package trade_point.data;

import trade_point.entity.TradePoint;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TradePointDTO {
    private TradePoint tradePoint;
    private Integer time;
    private Double distance;
}
