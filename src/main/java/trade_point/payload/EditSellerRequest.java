package trade_point.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Data
@NoArgsConstructor
public class EditSellerRequest {
    @NotBlank
    @Email
    private String email;
}
