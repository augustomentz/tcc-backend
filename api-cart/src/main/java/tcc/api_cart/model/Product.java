package tcc.api_cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    private String id;
    private String name;
    private String description;
    private Double price;
    private Double discount;
    private Double priceWithDiscount;
    private Integer rating;
    private Integer stock;
    private String img;
}