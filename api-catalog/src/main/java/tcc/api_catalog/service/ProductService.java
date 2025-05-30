package tcc.api_catalog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import tcc.api_catalog.model.Product;

public interface ProductService {
    Product create(Product product);

    Boolean batchCreate(List<Product> products);

    Product update(Product product);

    List<Product> findAll();

    String deleteById(String id);

    Product findById(String id);

    Product updateRating(String id, Integer newRating);
}
