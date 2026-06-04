package com.java_advanced.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Intercepte les erreurs manuelles (ex: Produit introuvable, Mauvais mot de passe)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        // Si c'est un problème de mot de passe, on renvoie une erreur 401 (Non autorisé)
        if (ex.getMessage() != null && ex.getMessage().contains("mot de passe")) {
            return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
        }

        // Sinon, c'est généralement un 404 (Introuvable)
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // filet de sécurité : attrape TOUTES les autres erreurs non prévues
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGlobalException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", "Une erreur inattendue est survenue sur le serveur.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}