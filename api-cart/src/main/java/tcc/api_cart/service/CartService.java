package tcc.api_cart.service;

import tcc.api_cart.model.Cart;

import java.util.List;

public interface CartService {
    Cart create(Cart product);

    Cart update(Cart product);

    List<Cart> findAll();

    String deleteById(String id);

    Cart findById(String id);
}
