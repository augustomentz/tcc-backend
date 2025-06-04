package tcc.api_cart.request;

import lombok.Data;
import tcc.api_cart.model.Origin;

@Data
public class UpdateItemRequest {
    String cartId;
    String itemId;
    Integer quantity;
    Origin origin;
}
