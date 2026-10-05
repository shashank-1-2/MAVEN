package com.example.myApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MyAppApplication {

	public static void main(String[] args) {
		//ALTERNATIVE APPROACH
		// SpringApplication.run(MyAppApplication.class, args);
		// Dev obj = new Dev();
		// obj.build();

		//Creating the Inversion Of Control(IoC) object for Storing all the Dependencies Injections
		ApplicationContext context = SpringApplication.run(MyAppApplication.class, args);
		//Injecting the Dependency
		Dev obj = context.getBean(Dev.class);
		obj.build();
	}

}
