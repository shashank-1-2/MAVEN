package com.example.webApp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.webApp.model.Product;
import com.example.webApp.repository.ProductRepo;

@Service
public class ProductService {

    private final ProductRepo repo;

    public ProductService(ProductRepo repo) {
        this.repo = repo;
    }

    public List<Product> getProducts() {
        return repo.findAll();
    }

    public Product getProductById(int prod_id) {
        return repo.findById(prod_id).orElse(new Product(100, "No Item", 0));
    }

    public void addProduct(Product prod) {
        repo.save(prod);
    }

    public void updateProduct(int prod_id, Product updateProduct) {
        updateProduct.setProd_id(prod_id);
        repo.save(updateProduct);   // save() updates when the id already exists
    }

    public void patchProduct(int prod_id, Product partial) {
        repo.findById(prod_id).ifPresent(curr -> {
            if (partial.getProd_name() != null) {
                curr.setProd_name(partial.getProd_name());
            }
            if (partial.getPrice() != null && partial.getPrice() > 0) {
                curr.setPrice(partial.getPrice());
            }
            repo.save(curr);
        });
    }

    public void deleteProduct(int prod_id) {
        repo.deleteById(prod_id);
    }
}