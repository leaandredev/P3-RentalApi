package com.rentalapp.rentalapi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;

    AuthService(AuthenticationManager authenticationManager, JWTService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    public String login(String email, String plainPassword) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        plainPassword));
        log.info("Authentication successful");
        return jwtService.generateToken(authentication);
    }

    public String register(User newUser, String plainPassword) {
        User user = userService.saveUser(newUser);
        return this.login(user.getEmail(), plainPassword);
    }
}
