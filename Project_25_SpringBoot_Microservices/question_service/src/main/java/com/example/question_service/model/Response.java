package com.example.question_service.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private Integer id;
    private String response;
}
