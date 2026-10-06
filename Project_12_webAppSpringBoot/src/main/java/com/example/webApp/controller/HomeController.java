package com.example.webApp.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //@Controller + @ResponseBody 
public class HomeController {
    
    @GetMapping ("/")
    public String greet(){
        System.out.println("Hello su lu lu lu ");
        return "Welcome to my web page";
    }

    @GetMapping (value = "/about", produces = "text/plain")
    public String about(){
        return "Nigaahon Mein Dekho Meri\r\n" + //
                        "Jo Hai Bas Gaya\r\n" + //
                        "Woh Hai Milata Tumse Hubahu\r\n" + //
                        "O O O Jaane Teri Aankhein Thi Ya\r\n" + //
                        "Baatein Thi Wajah\r\n" + //
                        "Huye Tum Jo Dil Ki Aarzoo\r\n" + //
                        "Tum Paas Hoke Bhi\r\n" + //
                        "Tum Aas Hoke Bhi\r\n" + //
                        "Ehsaas Hoke Bhi\r\n" + //
                        "Apne Nahi Aise Hai\r\n" + //
                        "Humko Gile Tumse Na Jaane Kyun\r\n" + //
                        "Milon Ke Hai Faasle\r\n" + //
                        "Tumse Na Jaane Kyun\r\n" + //
                        "Tu Jaane Na Aaa\r\n";
    }
}
