package tcc.api_catalog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tcc.api_catalog.model.Product;
import tcc.api_catalog.repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return this.productRepository.insert(product);
    }

    @Override
    public Boolean batchCreate(List<Product> products) {
        try {
            products.forEach(this::create);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Product findById(String id) {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public String deleteById(String id) {
        this.productRepository.deleteById(id);

        return id;
    }

    @Override
    public Product update(Product product) {
        Product existingProduct = this.productRepository.findById(product.getId())
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + product.getId()));
        
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setRating(product.getRating());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setPriceWithDiscount(product.getPriceWithDiscount());
        existingProduct.setStock(product.getStock());
        existingProduct.setImg(product.getImg());
        
        return this.productRepository.save(existingProduct);
    }

    @Override
    public Product updateRating(String id, Integer newRating) {
        Product existingProduct = this.productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        existingProduct.setRating(newRating);

        return this.productRepository.save(existingProduct);
    }
}
