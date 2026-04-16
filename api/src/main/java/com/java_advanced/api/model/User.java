package com.java_advanced.api.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import lombok.Data;

@Data
@Document(collection = "users")

public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;


}

