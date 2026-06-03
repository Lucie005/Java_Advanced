package com.java_advanced.api.security;

import com.java_advanced.api.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // On cherche l'en-tête "Authorization" dans la requête
        String authHeader = request.getHeader("Authorization");

        // Si on trouve un token qui commence par "Bearer " (le format standard)
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7); // On enlève le mot "Bearer " pour garder juste le code
            try {
                // On extrait l'email
                String email = jwtService.extractEmail(token);

                // Si le token est valide, on donne l'accès officiel à Spring Security
                if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            } catch (Exception e) {
                // Si le token est faux ou expiré, on ne fait rien, l'accès sera refusé
            }
        }
        // On laisse la requête continuer son chemin
        filterChain.doFilter(request, response);
    }
}