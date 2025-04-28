package tcc.api_catalog.service;

import java.util.List;
import tcc.api_catalog.model.Product;

public interface ProductService {
    Product create(Product product);

    Product update(Product product);

    List<Product> findAll();

    String deleteById(String id);

    Product findById(String id);
}
