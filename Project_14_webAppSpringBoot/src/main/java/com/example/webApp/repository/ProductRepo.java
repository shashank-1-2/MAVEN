package com.example.webApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.webApp.model.Product;


public interface ProductRepo extends JpaRepository<Product, Integer> {
    
}