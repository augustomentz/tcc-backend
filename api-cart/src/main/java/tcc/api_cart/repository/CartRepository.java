package tcc.api_cart.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tcc.api_cart.model.Cart;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {
    // Basic CRUD operations are automatically provided by MongoRepository
    // Add custom query methods here if needed
}
