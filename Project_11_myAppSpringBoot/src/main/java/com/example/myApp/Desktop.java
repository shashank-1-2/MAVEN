package com.example.myApp;

// import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component 
// @Primary -> This make the first choise for compiler when Confusion occurs
public class Desktop implements Computer{

    public void compile(){
        System.out.println("\nCompiling with Desktop");
    }
}
