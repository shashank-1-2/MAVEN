package com.example.webApp.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webApp.model.Product;

@Service 
public class ProductService {
    
    List<Product> products = new ArrayList<>(Arrays.asList(
        new Product(101, "Iphone", 50000), 
        new Product(102, "Samsung", 40000),
        new Product(103, "Huwawi", 45000)
    ));

    public List<Product> getProducts(){
        return products;
    }

    //GET
    public Product getProductById(int prod_id) {
        return products.stream()
                       .filter(p -> p.getProd_id() == prod_id)
                       .findFirst()
                       .orElse(new Product(100, "No Item", 0));
    }

    //POST
    public void addProduct(Product prod){
        products.add(prod);
    }

    //PUT
    public void updateProduct(int prod_id, Product updateProduct){
        for(int i = 0; i < products.size(); i++){
            if(products.get(i).getProd_id() == prod_id){
                products.set(i, updateProduct);
                return;
            }
        }
    }

    //PATCH
    public void patchProduct(int prod_id, Product partialProduct){
        for(int i = 0; i < products.size(); i++){
            Product curr = products.get(i);
            if(curr.getProd_id() == prod_id){
                if(partialProduct.getProd_name() != null){
                    curr.setProd_name(partialProduct.getProd_name());
                }
                // Check for null before evaluating > 0 to prevent a NullPointerException
                if(partialProduct.getPrice() != null && partialProduct.getPrice() > 0){
                    curr.setPrice(partialProduct.getPrice());
                }
                return;
            }
        }
    }

    //DELETE
    public void deleteProduct(int prod_id){
        products.removeIf(p -> p.getProd_id() == prod_id);
    }
}