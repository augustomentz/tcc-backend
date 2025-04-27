package tcc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tcc.model.Product;
import tcc.service.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @GetMapping(value = "/products", produces = "application/json")
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();

        products.add(
                Product.builder()
                        .id("1")
                        .name("Product One")
                        .description("Product one description")
                        .img("img_url")
                        .price(200.30)
                        .stock(20)
                        .build()
        );

        return products;
    }
}