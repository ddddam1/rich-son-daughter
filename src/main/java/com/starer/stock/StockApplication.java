package com.starer.stock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class StockApplication {

//	@Autowired
//	private CustomerRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(StockApplication.class, args);
	}

}