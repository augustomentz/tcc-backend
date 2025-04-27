package tcc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {
    String id;
    String name;
    String description;
    Double price;
    Integer stock;
    String img;
}
