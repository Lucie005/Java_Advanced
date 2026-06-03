package com.java_advanced.api.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // On génère une clé secrète ultra-sécurisée
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Le token sera valide pendant 24 heures (en millisecondes)
    private static final long EXPIRATION_TIME = 86400000;

    // La méthode qui fabrique le JWT
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email) // On met l'email au centre du badge
                .setIssuedAt(new Date()) // Date de création
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Date d'expiration
                .signWith(SECRET_KEY) // On signe avec notre tampon secret
                .compact(); // On assemble le tout en une chaîne de texte
    }

    // La méthode pour lire le token et y récupérer l'email
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}