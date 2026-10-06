package com.example.webApp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.webApp.model.Product;
import com.example.webApp.services.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController 
@RequestMapping("/products")
public class ProductController {
    
    private final ProductService service;

    // Spring automatically injects the dependency here. 
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/{prod_id}")
    public Product getProductById(@PathVariable int prod_id){
        return service.getProductById(prod_id);
    }

    @PostMapping
    public void addProduct(@RequestBody Product prod){
        service.addProduct(prod);
    }

    @PutMapping("/{prod_id}")
    public void updateProduct(@PathVariable int prod_id, @RequestBody Product prod) {
        service.updateProduct(prod_id, prod);
    }

    @PatchMapping("/{prod_id}")
    public void patchProduct(@PathVariable int prod_id, @RequestBody Product prod){
        service.patchProduct(prod_id, prod);
    }
    
    @DeleteMapping("/{prod_id}")
    public void deleteProduct(@PathVariable int prod_id){
        service.deleteProduct(prod_id);
    }
}