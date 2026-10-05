package com.example.myApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component 
public class Dev {
    
    //@Autowired  //Field Injection
    private Computer computer;

    //Constructor Injection
    // public Dev(Computer computer){
    //     this.laptop = laptop;
    // }

    //Setter Injection
    @Autowired 
    @Qualifier("laptop")
    public void setLaptop(Computer computer){
        this.computer = computer;
    }
    
    public void build(){

        computer.compile();

        System.out.println( "\nMil jaaoon main tumhein agar raaston mein kahin\r\n" + //
                            "Tum rasman hi pooch lena mujhse haal mera\r\n" + //
                            "Main muskura kar, gham chhupa kar hi jawaab doonga\r\n" + //
                            "Main yahi karta aaya hoon, ye hai kamaal mera");
    }
}
