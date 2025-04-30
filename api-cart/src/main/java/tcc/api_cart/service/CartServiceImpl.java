package tcc.api_cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tcc.api_cart.model.Cart;
import tcc.api_cart.repository.CartRepository;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart create(Cart product) {
        return this.cartRepository.insert(product);
    }

    @Override
    public Cart findById(String id) {
        return this.cartRepository.findById(id).orElseGet(() -> this.create(Cart.builder().id(id).build()));
    }

    @Override
    public List<Cart> findAll() {
        return this.cartRepository.findAll();
    }

    @Override
    public String deleteById(String id) {
        this.cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + id));

        this.cartRepository.deleteById(id);

        return id;
    }

    @Override
    public Cart update(Cart cart) {
        Cart existingProduct = this.cartRepository.findById(cart.getId())
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cart.getId()));

        return this.cartRepository.save(existingProduct);
    }
}
