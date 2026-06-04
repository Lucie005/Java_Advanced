package com.java_advanced.api.controller;

import com.java_advanced.api.model.Product;
import com.java_advanced.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // C'est cette méthode qui va répondre au POST
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // C'est cette méthode qui va répondre au GET
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Route pour modifier un produit
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    // Route pour supprimer un produit
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }
}