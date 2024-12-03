package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Value("${security.endpoints.public}")
    private String publicEndpoint;

    @Value("${security.endpoints.private}")
    private String privateEndpoint;

    @Value("${security.auth.jwt}")
    private boolean jwtAuthEnabled;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(publicEndpoint).permitAll()
                        .requestMatchers(privateEndpoint).authenticated()
                );

        if (jwtAuthEnabled) {
            http.oauth2ResourceServer(oauth2 -> oauth2.jwt());
        }
        return http.build();
    }
}