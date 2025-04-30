package tcc.api_checkout.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import tcc.api_checkout.model.Sale;

@Repository
public interface SaleRepository extends MongoRepository<Sale, String> {
    // Basic CRUD operations are automatically provided by MongoRepository
    // Add custom query methods here if needed
}