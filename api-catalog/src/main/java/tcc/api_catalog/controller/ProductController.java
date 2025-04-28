package tcc.api_catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tcc.api_catalog.model.Product;
import tcc.api_catalog.service.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductServiceImpl productService;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return this.productService.create(product);
    }

    @PutMapping
    public Product update(@RequestBody Product product) {
        return this.productService.update(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return this.productService.findById(id);
    }

    @GetMapping
    public List<Product> list() {
        return this.productService.findAll();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        return this.productService.deleteById(id);
    }
}