package com.java_advanced.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.java_advanced.api.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

}