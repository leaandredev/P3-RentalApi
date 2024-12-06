package com.rentalapp.rentalapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.dto.request.LoginRequest;
import com.rentalapp.rentalapi.dto.request.RegisterRequest;
import com.rentalapp.rentalapi.dto.response.ErrorResponse;
import com.rentalapp.rentalapi.dto.response.TokenResponse;
import com.rentalapp.rentalapi.dto.response.UserResponse;
import com.rentalapp.rentalapi.mapper.UserMapper;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.service.AuthService;
import com.rentalapp.rentalapi.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final UserService userService;

    AuthController(AuthService authService, UserMapper userMapper, UserService userService) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Operation(description = "Connexion de l'utilisateur")
    @ApiResponse(responseCode = "200", description = "Authentification réussie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "401", description = "Échec de l'authentification", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping(value = "/login", consumes = { "application/json" })
    @ResponseBody

    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity
                .ok(new TokenResponse(token));
    }

    @Operation(description = "Enregistrement de l'utilisateur")
    @ApiResponse(responseCode = "200", description = "Enregistrement et authentification réussie", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "400", description = "Échec de l'enregistrement")
    @PostMapping(value = "/register", consumes = { "application/json" })
    @ResponseBody
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = userMapper.requestToEntity(registerRequest);
        String token = authService.register(user, registerRequest.getPassword());
        return ResponseEntity
                .ok(new TokenResponse(token));

    }

    @Operation(description = "Récupération des informations de l'utilisateur authentifié")
    @ApiResponse(responseCode = "200", description = "Récupération réussie", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "401", description = "Connexion non autorisé")
    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity<?> me(Authentication authentication) {
        User authenticatedUser = userService.getUserByEmail(authentication.getName());
        return ResponseEntity.ok(userMapper.entityToResponse(authenticatedUser));
    }

}
