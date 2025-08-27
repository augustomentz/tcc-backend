package tcc.api_catalog.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.api_catalog.model.ApiResponse;
import tcc.api_catalog.model.Product;
import tcc.api_catalog.service.ProductServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts() {
        return ResponseEntity.ok(ApiResponse.success(productService.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable String id) {
        try {
            Product product = this.productService.findById(id);

            if (product == null) {
                return ResponseEntity.ok(ApiResponse.notFound());
            }

            return ResponseEntity.ok(ApiResponse.success(product));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest());
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        try {
            Product createdProduct = this.productService.create(product);

            return ResponseEntity.ok(ApiResponse.success(createdProduct));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> updateProduct(
            @PathVariable String id,
            @RequestBody Product product) {
        try {
            Product updatedProduct = this.productService.update(product);

            return ResponseEntity.ok(ApiResponse.success(updatedProduct));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable String id) {
        try {
            this.productService.deleteById(id);

            return ResponseEntity.ok(ApiResponse.success(null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest());
        }
    }

    @PostMapping("/batch")
    public ResponseEntity<ApiResponse<Boolean>> batchCreate(@RequestBody List<Product> products) {
        try {
            Boolean result = this.productService.batchCreate(products);

            return ResponseEntity.ok(ApiResponse.success(result));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest());
        }
    }

    @PutMapping("/{id}/{rating}")
    public ResponseEntity<ApiResponse<Product>> updateRating(@PathVariable String id, @PathVariable Integer rating) {
        try {
            return ResponseEntity.ok(ApiResponse.success(this.productService.updateRating(id, rating)));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(ApiResponse.badRequest());
        }
    }
}