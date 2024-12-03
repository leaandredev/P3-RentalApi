package com.rentalapp.rentalapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.dto.ErrorResponse;
import com.rentalapp.rentalapi.dto.LoginRequest;
import com.rentalapp.rentalapi.dto.RegisterRequest;
import com.rentalapp.rentalapi.dto.TokenResponse;
import com.rentalapp.rentalapi.dto.UserResponse;
import com.rentalapp.rentalapi.model.DbUser;
import com.rentalapp.rentalapi.repository.UserRepository;
import com.rentalapp.rentalapi.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    private final UserRepository userRepository;

    AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    @Operation(description = "Connexion de l'utilisateur")
    @ApiResponse(responseCode = "200", description = "Authentification réussie", content = @Content(schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "401", description = "Échec de l'authentification", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping(value = "/login", consumes = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity
                    .ok(authService.authentificate(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (Exception e) {
            System.err.println("Authentication failed: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
        }
    }

    @Operation(description = "Enregistrement de l'utilisateur")
    @ApiResponse(responseCode = "200", description = "Enregistrement et authentification réussie", content = @Content(schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "400", description = "Échec de l'enregistrement")
    @PostMapping(value = "/register", consumes = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            return ResponseEntity.ok(authService.login(registerRequest));
        } catch (Exception e) {
            System.err.println("Register failed: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

    }

    @Operation(description = "Récupération des informations de l'utilisateur authentifié")
    @ApiResponse(responseCode = "200", description = "Récupération réussie", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "401", description = "Connexion non autorisé")
    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity<?> me(Authentication authentication) {
        try {
            return ResponseEntity.ok(authService.getAuthenticatedUser(authentication.getName()));
        } catch (Exception e) {
            System.err.println("get me information failed: " + e.getClass().getName() + " - " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

}
