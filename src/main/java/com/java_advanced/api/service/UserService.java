package com.java_advanced.api.service;

import com.java_advanced.api.model.User;
import com.java_advanced.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Créer un utilisateur (Inscription)
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Récupérer tous les utilisateurs (Pour l'admin)
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}