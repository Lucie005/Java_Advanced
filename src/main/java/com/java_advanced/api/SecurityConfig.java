package com.java_advanced.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Obligatoire pour autoriser les requêtes POST
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // On ouvre GRAND toutes les portes du TP
                );
        return http.build();
    }

    // En ajoutant ce bloc, Spring voit qu'on gère la config et ARRÊTE de générer son mot de passe
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("temp")
                .password("temp")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}