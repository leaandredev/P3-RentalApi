package com.rentalapp.rentalapi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.RegisterRequest;
import com.rentalapp.rentalapi.dto.TokenResponse;
import com.rentalapp.rentalapi.dto.UserResponse;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final UserService userService;

    private final UserRepository userRepository;

    AuthService(AuthenticationManager authenticationManager, JWTService jwtService, UserService userService,
            UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    public TokenResponse login(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password));
        log.info("Authentication successful");
        return new TokenResponse(jwtService.generateToken(authentication));
    }

    public TokenResponse register(RegisterRequest registerRequest) {
        User user = userService.createUser(registerRequest);
        return this.login(user.getEmail(), registerRequest.getPassword());
    }

    public UserResponse getAuthenticatedUser(String email) {
        User user = userRepository.findByEmail(email);
        return new UserResponse(user);
    }
}
