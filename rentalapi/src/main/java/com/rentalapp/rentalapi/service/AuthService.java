package com.rentalapp.rentalapi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.model.User;

import lombok.extern.slf4j.Slf4j;

/* 
 * Service which handle authentication and user registration
 */
@Service
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    private final UserService userService;

    public AuthService(AuthenticationManager authenticationManager, JWTService jwtService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    /**
     * Authenticate a user and return a new JwtToken
     * 
     * @param email         the email of the user attempting to log in
     * @param plainPassword the plain-text password of the user
     * @return a new jwtToken provide by jwtService
     */
    public String login(String email, String plainPassword) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        plainPassword));
        log.info("Authentication successful");
        return jwtService.generateToken(authentication);
    }

    /**
     * Save a new user to the database, authenticate him and return a JwtToken
     * 
     * @param newUser       The user to register
     * @param plainPassword the plain-text password of the new user
     * @return a new jwtToken for the newly registered user
     */
    public String register(User newUser, String plainPassword) {
        User user = userService.saveUser(newUser);
        return login(user.getEmail(), plainPassword);
    }
}
