package tcc.api_cart.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import tcc.api_cart.model.ApiResponse;
import tcc.api_cart.model.Product;

@FeignClient(name = "api-catalog", url = "${app.api-catalog-url}")
public interface ApiCatalogFeign {
    @GetMapping("/products/{id}")
    ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable String id);
}