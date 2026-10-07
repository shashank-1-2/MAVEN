package com.example.SpringBootJDBC;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.SpringBootJDBC.model.Alien;
import com.example.SpringBootJDBC.repository.AlienRepo;

@SpringBootApplication
public class SpringBootJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBootJdbcApplication.class, args);

		Alien alien1 = context.getBean(Alien.class);
		alien1.setId(111);
		alien1.setName("Shashank");
		alien1.setTech("Java");

		AlienRepo repo = context.getBean(AlienRepo.class);
		repo.save(alien1);

		System.out.println(repo.findAll());
	}
}