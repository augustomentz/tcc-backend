package tcc.api_checkout;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "tcc.api_checkout.repository")
public class ApiCheckoutApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiCheckoutApplication.class, args);
	}

}
