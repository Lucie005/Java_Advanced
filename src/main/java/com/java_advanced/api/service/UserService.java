package com.java_advanced.api.service;

import com.java_advanced.api.model.User;
import com.java_advanced.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Créer un utilisateur (Inscription)
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Si aucun rôle n'est précisé, on lui donne le rôle normal
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("ROLE_USER");
        }

        return userRepository.save(user);
    }

    // Le Login (Génération du token)
    public String login(String email, String rawPassword) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return jwtService.generateToken(user.getEmail(), user.getRole());
            }
        }
        throw new RuntimeException("Email ou mot de passe incorrect");
    }

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}