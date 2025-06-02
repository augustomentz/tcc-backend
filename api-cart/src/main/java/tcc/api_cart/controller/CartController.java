package tcc.api_cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.api_cart.model.ApiResponse;
import tcc.api_cart.model.Cart;
import tcc.api_cart.service.CartServiceImpl;


import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartServiceImpl cartService;

    @PostMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> create(@PathVariable String id) {
        return ResponseEntity.ok(ApiResponse.success("id"));
    }

    @PostMapping("/{cartId}/{itemId}")
    public ResponseEntity<ApiResponse<Boolean>> addItem(@PathVariable String cartId, @PathVariable String itemId) {
        return ResponseEntity.ok(ApiResponse.success(this.cartService.addItem(cartId, itemId)));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<Cart>> update(@RequestBody Cart cart) {
        try {
            return ResponseEntity.ok(ApiResponse.success(this.cartService.update(cart)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.badRequest());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Cart>> getCartById(@PathVariable String id) {
        try {
            return ResponseEntity.ok(ApiResponse.success(this.cartService.findById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.badRequest());
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Cart>>> list() {
        try {
            return ResponseEntity.ok(ApiResponse.success(this.cartService.findAll()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.badRequest());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String id) {
        try {
            return ResponseEntity.ok(ApiResponse.success(this.cartService.deleteById(id)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.badRequest());
        }
    }

    @DeleteMapping("/{cartId}/{itemId}")
    public ResponseEntity<ApiResponse<Boolean>> deleteItem(@PathVariable String cartId, @PathVariable String itemId) {
        try {
            return ResponseEntity.ok(ApiResponse.success(this.cartService.deleteItem(cartId, itemId)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ApiResponse.badRequest());
        }
    }
}