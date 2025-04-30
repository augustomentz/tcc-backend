package tcc.api_cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "tcc.api_cart.repository")
public class ApiCartApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiCartApplication.class, args);
	}
}
