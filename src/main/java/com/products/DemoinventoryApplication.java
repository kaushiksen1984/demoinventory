package com.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.products.repository")
@SpringBootApplication
public class DemoinventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoinventoryApplication.class, args);
	}

}
