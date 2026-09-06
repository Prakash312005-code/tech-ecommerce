package com.tech.gadget.tech.ecommerce.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(
            JwtAuthenticationFilter jwtAuthenticationFilter
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http

            // Enable CORS
            .cors(cors -> {})

            // Disable CSRF
            .csrf(csrf -> csrf.disable())

            // JWT does not use session
            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            // Authorization Rules
            .authorizeHttpRequests(auth -> auth

                // Login and Register are public
                .requestMatchers("/api/auth/**").permitAll()

                // Products are public
                .requestMatchers("/api/products/**").permitAll()

                // Cart requires JWT
                .requestMatchers("/api/cart/**").authenticated()

                // Orders require JWT
                .requestMatchers("/api/orders/**").authenticated()

                // Everything else
                .anyRequest().permitAll()
            )

            // Add JWT Filter
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }


    // CORS Configuration
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration =
                new CorsConfiguration();

        // React frontend URL
        configuration.setAllowedOrigins(
                List.of("http://localhost:3000")
        );

        // Allowed HTTP Methods
        configuration.setAllowedMethods(
                List.of(
                    "GET",
                    "POST",
                    "PUT",
                    "DELETE",
                    "OPTIONS"
                )
        );

        // Allow headers including JWT Authorization
        configuration.setAllowedHeaders(
                List.of("*")
        );

        // Expose headers
        configuration.setExposedHeaders(
                List.of("Authorization")
        );


        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }


    // BCrypt Password Encoder
    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}