package tcc.api_cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tcc.api_cart.feign.ApiCatalogFeign;
import tcc.api_cart.model.Origin;
import tcc.api_cart.response.ApiResponse;
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
        cart.setShipping(cart.getShipping());
        cart.setTotal(0.0);
        cart.setDiscount(0.0);
        cart.setDiscountPercentage(0.0);
        cart.setProducts(List.of());

        return this.cartRepository.insert(cart);
    }

    @Override
    public Cart findById(String id) {
        int shipping = new Random().nextInt(41) + 5;

        return this.cartRepository
            .findById(id)
            .orElseGet(() ->
                this.create(
                    Cart
                        .builder()
                        .id(id)
                        .shipping((double) shipping)
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
    public Boolean addItem(String cartId, String itemId, Integer quantity, Origin origin) {
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
                    .priceWithDiscount(product.getPriceWithDiscount())
                    .discount(product.getDiscount())
                    .stock(product.getStock())
                    .quantity(1)
                    .build()
            );
        } else {
            if (Objects.nonNull(origin) && origin.equals(Origin.CATALOG)) {
                existingProduct.get().setQuantity(existingProduct.get().getQuantity() + 1);
            } else {
                existingProduct.get().setQuantity(quantity);
            }
        }

        try {
            calculateAndSaveCart(cart);

            return true;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean deleteItem(String cartId, String itemId) {
        Cart cart = this.findById(cartId);

        final boolean removed = cart.getProducts().removeIf(item -> item.getId().equals(itemId));

        if (removed) {
            this.calculateAndSaveCart(cart);
        }

        return removed;
    }

    @Override
    public Cart calculateAndSaveCart(Cart cart) {
        cart.getProducts().forEach(product -> {
            double totalProduct = product.getPriceWithDiscount() * product.getQuantity();

            product.setTotal(totalProduct);
        });
    
        Double total = cart.getProducts().stream()
            .mapToDouble(product -> product.getPrice() * product.getQuantity())
            .sum(); // sum total without discount considering quantity
        Double totalWithDiscount = cart.getProducts().stream()
            .mapToDouble(product -> product.getPriceWithDiscount() * product.getQuantity())
            .sum(); // sum total with discount considering quantity
            
        Double discount = total - totalWithDiscount;
        Double discountPercentage = 0.0;
    
        if (total > 0) {
            discountPercentage = (discount * 100) / total;
        }
    
        cart.setTotal(totalWithDiscount);
        cart.setDiscount(discount);
        cart.setDiscountPercentage(discountPercentage);
    
        return this.cartRepository.save(cart);
    }
}
