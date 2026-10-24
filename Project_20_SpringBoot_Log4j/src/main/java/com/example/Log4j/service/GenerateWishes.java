package com.example.Log4j.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service 
public class GenerateWishes implements IGreet {
    
    @Override 
    public String generateWishes(){
        LocalDateTime dateTime = LocalDateTime.now();
        int hour = dateTime.getHour();

        String body = null;

        if(hour < 12){
            body = "Good Morning";
        }
        else if(hour < 16){
            body = "Good Afternoon";
        }
        else if(hour < 20){
            body = "Good Evening";
        }
        else{
            body = "Good Night";
        }

        return body;

    }
}
