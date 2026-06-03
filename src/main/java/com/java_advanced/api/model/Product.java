package com.java_advanced.api.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
@Document(collection = "products")

public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private double price;
    private String userId;



}
