package tcc.api_cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tcc.api_cart.feign.ApiCatalogFeign;
import tcc.api_cart.model.ApiResponse;
import tcc.api_cart.model.Cart;
import tcc.api_cart.model.CartProduct;
import tcc.api_cart.model.Product;
import tcc.api_cart.repository.CartRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
@Transactional
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ApiCatalogFeign apiCatalog;

    @Override
    public Cart create(Cart cart) {
        cart.setDeliveryFee(cart.getDeliveryFee());
        cart.setTotal(0.0);
        cart.setDiscount(0.0);
        cart.setDiscountPercentage(0.0);
        cart.setProducts(List.of());

        return this.cartRepository.insert(cart);
    }

    @Override
    public Cart findById(String id) {
        int fee = new Random().nextInt(41) + 5;

        return this.cartRepository
            .findById(id)
            .orElseGet(() ->
                this.create(
                    Cart
                        .builder()
                        .id(id)
                        .deliveryFee((double) fee)
                        .build()
                )
            );
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

    @Override
    public Boolean addItem(String cartId, String itemId) {
        Cart cart = this.findById(cartId);

        ResponseEntity<ApiResponse<Product>> response = this.apiCatalog.getProductById(itemId);
        Product product = Objects.requireNonNull(response.getBody()).getData();

        if (product.getStock() < 1) {
            throw new RuntimeException("Product sold out");
        }

        Optional<CartProduct> existingProduct = cart.getProducts().stream()
                .filter(item -> item.getId().equals(itemId))
                .findFirst();

        if (existingProduct.isEmpty()) {
            cart.getProducts().add(
                CartProduct
                    .builder()
                    .id(itemId)
                    .img(product.getImg())
                    .name(product.getName())
                    .price(product.getPrice())
                    .stock(product.getStock())
                    .discount(product.getDiscount())
                    .quantity(1)
                    .build()
            );
        } else {
            existingProduct.get().setQuantity(existingProduct.get().getQuantity() + 1);
        }

        this.cartRepository.save(cart);

        return true;
    }

    @Override
    public Boolean deleteItem(String cartId, String itemId) {
        Cart cart = this.findById(cartId);

        final boolean removed = cart.getProducts().removeIf(item -> item.getId().equals(itemId));

        if (removed) {
            this.cartRepository.save(cart);
        }

        return removed;
    }

    @Override
    public void calculateCart(Cart cart) {
    }
}
