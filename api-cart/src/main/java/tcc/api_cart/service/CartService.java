package tcc.api_cart.service;

import tcc.api_cart.model.Cart;
import tcc.api_cart.model.CartProduct;
import tcc.api_cart.model.Origin;

import java.util.List;

public interface CartService {
    Cart create(Cart cart);

    Cart update(Cart cart);

    List<Cart> findAll();

    String deleteById(String id);

    Cart findById(String id);

    Boolean addItem(String cartId, String itemId, Integer quantity, Origin origin);

    Boolean deleteItem(String cartId, String itemId);

    Cart calculateAndSaveCart(Cart cart);
}
