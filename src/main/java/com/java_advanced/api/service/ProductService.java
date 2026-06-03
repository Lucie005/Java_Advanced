package com.java_advanced.api.service;

import com.java_advanced.api.model.Product;
import com.java_advanced.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Pour créer un produit
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    // Pour récupérer tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}