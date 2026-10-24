package com.example.Log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Log4j.service.IGreet;


@SpringBootApplication
public class Log4jApplication implements CommandLineRunner {

    private static final Logger logger = LogManager.getLogger(Log4jApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(Log4jApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        logger.info("--> Log4j2 INFO log initialized successfully! <--");
        logger.warn("--> Log4j2 WARN log test! <--");
        logger.error("--> Log4j2 ERROR log test! <--");
        
        System.out.println(greet.generateWishes());
    }

    
    private final IGreet greet;

    public Log4jApplication(IGreet greet) {
        this.greet = greet;
    }
}
