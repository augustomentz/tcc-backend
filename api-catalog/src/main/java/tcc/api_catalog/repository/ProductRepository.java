package tcc.api_catalog.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tcc.api_catalog.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    // Basic CRUD operations are automatically provided by MongoRepository
    // Add custom query methods here if needed
}
