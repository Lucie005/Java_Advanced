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

    // On injecte notre outil de cryptage
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // Créer un utilisateur (Inscription sécurisée)
    public User createUser(User user) {
        // On crypte le mot de passe de l'utilisateur avant de l'enregistrer
        String motDePasseCrypte = passwordEncoder.encode(user.getPassword());
        user.setPassword(motDePasseCrypte);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // NOUVELLE MÉTHODE : Le Login
    public String login(String email, String rawPassword) {
        // 1. On cherche l'utilisateur par son email
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // 2. On compare le mot de passe tapé avec celui crypté en base
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                // 3. Si c'est bon, on génère le token
                return jwtService.generateToken(user.getEmail());
            }
        }
        // Si mauvais email ou mauvais mot de passe, on déclenche une erreur
        throw new RuntimeException("Email ou mot de passe incorrect");
    }
}

