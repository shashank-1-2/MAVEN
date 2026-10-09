package com.example.webApp;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.webApp.model.Product;
import com.example.webApp.repository.ProductRepo;

@SpringBootApplication
public class WebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebAppApplication.class, args);
	}

	@Bean
	CommandLineRunner seed(ProductRepo repo) {
		return args -> repo.saveAll(List.of(
			new Product(101, "Iphone", 50000),
			new Product(102, "Samsung", 40000),
			new Product(103, "Huwawi", 45000)
		));
	}

}