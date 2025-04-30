package tcc.api_cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tcc.api_cart.model.Cart;
import tcc.api_cart.service.CartServiceImpl;


import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartServiceImpl cartService;

    @PutMapping
    public Cart update(@RequestBody Cart cart) {
        return this.cartService.update(cart);
    }

    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable String id) {
        return this.cartService.findById(id);
    }

    @GetMapping
    public List<Cart> list() {
        return this.cartService.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return this.cartService.deleteById(id);
    }
}