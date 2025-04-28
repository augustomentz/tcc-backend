package tcc.api_catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "tcc.api_catalog.repository")
public class ApiCatalogApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiCatalogApplication.class, args);
	}
}
