package tcc.api_checkout.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "sales")
public class Sale {
    @Id
    private String id;
    private Double total;
    private Address address;
    private Payment payment;
    private String cartId;
    private Integer productAmount;
}
