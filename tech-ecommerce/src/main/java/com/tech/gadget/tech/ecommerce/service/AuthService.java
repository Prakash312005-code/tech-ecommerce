package com.tech.gadget.tech.ecommerce.service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tech.gadget.tech.ecommerce.dto.LoginRequest;
import com.tech.gadget.tech.ecommerce.dto.LoginResponse;
import com.tech.gadget.tech.ecommerce.dto.RegisterRequest;
import com.tech.gadget.tech.ecommerce.entity.User;
import com.tech.gadget.tech.ecommerce.repository.UserRepository;
import com.tech.gadget.tech.ecommerce.security.JwtService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    // Constructor
    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }


    // ================= REGISTER =================

    public String register(RegisterRequest request) {

        // Check duplicate username
        if (userRepository.existsByUsername(request.getUsername())) {
            return "Username already exists";
        }

        // Check duplicate email
        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        // Create new user
        User user = new User();

        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        // Encrypt password using BCrypt
        user.setPassword(
                passwordEncoder.encode(request.getPassword())
        );

        // Save user
        userRepository.save(user);

        return "Registration successful";
    }


    // ================= LOGIN =================

    public LoginResponse login(LoginRequest request) {

        // Find user by username
        User user = userRepository
                .findByUsername(request.getUsername())
                .orElse(null);


        // User not found
        if (user == null) {
            return new LoginResponse(
                    "Invalid username or password",
                    null
            );
        }


        // Check BCrypt password
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        )) {

            return new LoginResponse(
                    "Invalid username or password",
                    null
            );
        }


        // Generate JWT Token
        String token = jwtService.generateToken(
                user.getUsername()
        );


        // Return success + token
        return new LoginResponse(
                "Login successful",
                token
        );
    }
}