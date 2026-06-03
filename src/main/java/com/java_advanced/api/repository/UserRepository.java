package com.java_advanced.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.java_advanced.api.model.User;
import java.util.Optional; // Ne pas oublier cet import

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    // Cette ligne  demande à Spring de générer tout seul la requête MongoDB
    Optional<User> findByEmail(String email);
}