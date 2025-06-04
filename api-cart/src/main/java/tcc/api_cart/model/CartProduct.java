package tcc.api_cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartProduct {
    String id;
    String name;
    String img;
    Double price;
    Double priceWithDiscount;
    Double discount;
    Double total;
    Integer quantity;
    Integer stock;
}
