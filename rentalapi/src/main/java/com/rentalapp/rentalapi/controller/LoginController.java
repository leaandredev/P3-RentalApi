package com.rentalapp.rentalapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.dto.ErrorResponse;
import com.rentalapp.rentalapi.dto.LoginRequest;
import com.rentalapp.rentalapi.dto.RegisterRequest;
import com.rentalapp.rentalapi.dto.UserResponse;
import com.rentalapp.rentalapi.model.DbUser;
import com.rentalapp.rentalapi.repository.UserRepository;
import com.rentalapp.rentalapi.service.UserService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/login", consumes = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity
                    .ok(userService.authentificateUser(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping(value = "/register", consumes = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            DbUser dbUser = userService.createUser(registerRequest);
            return ResponseEntity.ok(userService.authentificateUser(dbUser.getEmail(), registerRequest.getPassword()));
        } catch (Exception e) {
            System.err.println("Register failed: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication) {
        try {
            DbUser dbUser = userRepository.findByEmail(authentication.getName());
            return ResponseEntity.ok(new UserResponse(dbUser));
        } catch (Exception e) {
            System.err.println("get me information failed: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
