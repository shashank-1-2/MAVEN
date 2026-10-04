package com.myapp;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

public class App 
{
    public static void main( String[] args ){
        
        ApplicationContext context = new ClassPathXmlApplicationContext("Spring.xml");
        Dev obj = context.getBean(Dev.class);

        // System.out.println(obj.age);

        // obj.setAge(18);
        // System.out.println(obj.getAge());

        obj.build();
    }
}
