package com.java_advanced.api.controller;

import com.java_advanced.api.model.User;
import com.java_advanced.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Route pour s'inscrire
    @PostMapping
    public User registerUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Route pour voir tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}