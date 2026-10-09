package com.example.webApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class Product {
    
    @Id 
    private Integer prod_id;
    private String prod_name;
    private Integer price;
    
    // Update the constructor
    public Product(Integer prod_id, String prod_name, Integer price) {
        this.prod_id = prod_id;
        this.prod_name = prod_name;
        this.price = price;
    }

    public Product() {}
    
    // Update the Getters and Setters to match 'Integer'
    public Integer getProd_id() {
        return prod_id;
    }
    public void setProd_id(Integer prod_id) {
        this.prod_id = prod_id;
    }
    public String getProd_name() {
        return prod_name;
    }
    public void setProd_name(String prod_name) {
        this.prod_name = prod_name;
    }
    public Integer getPrice() {
        return price;
    }
    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [prod_id=" + prod_id + ", prod_name=" + prod_name + ", price=" + price + "]";
    }

    
}