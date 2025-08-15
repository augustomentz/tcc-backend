package tcc.api_checkout.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String street;
    private String city;
    private String country;
    private String zip;
}
