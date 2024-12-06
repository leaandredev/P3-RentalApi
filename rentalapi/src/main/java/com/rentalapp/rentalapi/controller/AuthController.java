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
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Tag(name = "Auth", description = "Operations related to authentication")
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

    @Operation(summary = "Login", description = "Log a user")
    @ApiResponse(responseCode = "200", description = "Authenticated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "401", description = "Invalid credentials, access denied", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)))
    @PostMapping(value = "/login", consumes = { "application/json" })
    @ResponseBody

    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity
                .ok(new TokenResponse(token));
    }

    @Operation(summary = "Register", description = "Register a new user")
    @ApiResponse(responseCode = "200", description = "Registerd successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
    @ApiResponse(responseCode = "400", description = "Invalid request for new user register")
    @PostMapping(value = "/register", consumes = { "application/json" })
    @ResponseBody
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = userMapper.requestToEntity(registerRequest);
        String token = authService.register(user, registerRequest.getPassword());
        return ResponseEntity
                .ok(new TokenResponse(token));

    }

    @Operation(summary = "Me", description = "Get authenticated user informations")
    @ApiResponse(responseCode = "200", description = "User data retrieved successfully", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "401", description = "Invalid credentials, access denied", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity<UserResponse> me(Authentication authentication) {
        User authenticatedUser = userService.getUserByEmail(authentication.getName());
        UserResponse userResponse = userMapper.entityToResponse(authenticatedUser);
        return ResponseEntity.ok(userResponse);
    }

}
