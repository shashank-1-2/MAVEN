package com.example.Docker;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // @ResponseBody + @Controller
public class HelloController {
    
    @RequestMapping("/")
    public String greet(){
        return "Hello World , I'm a JAVA DEVELOPER";
    }
}
