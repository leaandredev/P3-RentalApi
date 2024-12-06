package com.rentalapp.rentalapi.documentation;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import com.rentalapp.rentalapi.dto.request.LoginRequest;
import com.rentalapp.rentalapi.dto.request.RegisterRequest;
import com.rentalapp.rentalapi.dto.response.TokenResponse;
import com.rentalapp.rentalapi.dto.response.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Auth", description = "Operations related to authentication")
public interface AuthControllerDocumentation {

    @Operation(summary = "Login", description = "Log a user")
    @ApiResponse(responseCode = "200", description = "Authenticated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
    public abstract ResponseEntity<TokenResponse> login(LoginRequest loginRequest);

    @Operation(summary = "Register", description = "Register a new user")
    @ApiResponse(responseCode = "200", description = "Registerd successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
    public abstract ResponseEntity<TokenResponse> register(RegisterRequest registerRequest);

    @Operation(summary = "Me", description = "Get authenticated user informations")
    @ApiResponse(responseCode = "200", description = "User data retrieved successfully", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    public abstract ResponseEntity<UserResponse> me(Authentication authentication);
}
