package com.rentalapp.rentalapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.documentation.AuthControllerDocumentation;
import com.rentalapp.rentalapi.dto.request.LoginRequest;
import com.rentalapp.rentalapi.dto.request.RegisterRequest;
import com.rentalapp.rentalapi.dto.response.TokenResponse;
import com.rentalapp.rentalapi.dto.response.UserResponse;
import com.rentalapp.rentalapi.mapper.UserMapper;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.service.AuthService;
import com.rentalapp.rentalapi.service.UserService;
import jakarta.validation.Valid;

/** Endpoints for authentication */
@RestController
@RequestMapping("/api/auth")
public class AuthController implements AuthControllerDocumentation {

    private final AuthService authService;
    private final UserMapper userMapper;
    private final UserService userService;

    AuthController(AuthService authService, UserMapper userMapper, UserService userService) {
        this.authService = authService;
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @Override
    @PostMapping(value = "/login", consumes = { "application/json" })
    @ResponseBody
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity
                .ok(new TokenResponse(token));
    }

    @Override
    @PostMapping(value = "/register", consumes = { "application/json" })
    @ResponseBody
    public ResponseEntity<TokenResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        User user = userMapper.requestToEntity(registerRequest);
        String token = authService.register(user, registerRequest.getPassword());
        return ResponseEntity
                .ok(new TokenResponse(token));

    }

    @Override
    @GetMapping("/me")
    @ResponseBody
    public ResponseEntity<UserResponse> me(Authentication authentication) {
        User authenticatedUser = userService.getUserByEmail(authentication.getName());
        UserResponse userResponse = userMapper.entityToResponse(authenticatedUser);
        return ResponseEntity.ok(userResponse);
    }

}
