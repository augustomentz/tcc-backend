package tcc.api_checkout.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    private String cardholder;
    private String cardNumber;
    private String expiry;
    private String cvv;
}
