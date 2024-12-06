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

/** AuthController Annotations for OpenAPI documentation */
@Tag(name = "Auth", description = "Endpoints for authentication")
public interface AuthControllerDocumentation {

    /**
     * Authentificate a user
     * 
     * @param loginRequest A requestBody with user email and password
     * @return a JwtToken for the user
     */
    @Operation(summary = "Login", description = "Authentificate a user")
    @ApiResponse(responseCode = "200", description = "Authenticated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
    public abstract ResponseEntity<TokenResponse> login(LoginRequest loginRequest);

    /**
     * Register and authentificate a new user
     * 
     * @param registerRequest A requestBody with user email, name and password
     * @return a JwtToken for the new user
     */
    @Operation(summary = "Register", description = "Register and authentificate a new user")
    @ApiResponse(responseCode = "200", description = "Registerd successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TokenResponse.class)))
    public abstract ResponseEntity<TokenResponse> register(RegisterRequest registerRequest);

    /**
     * Get authenticated user informations from authentication
     * 
     * @param authentication Represent the token for the authenticated user
     * @return the details of the user entity
     */
    @Operation(summary = "Me", description = "Get authenticated user informations")
    @ApiResponse(responseCode = "200", description = "User data retrieved successfully", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    public abstract ResponseEntity<UserResponse> me(Authentication authentication);
}
