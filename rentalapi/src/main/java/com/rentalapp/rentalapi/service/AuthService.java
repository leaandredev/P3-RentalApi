package com.rentalapp.rentalapi.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.request.RegisterRequest;
import com.rentalapp.rentalapi.dto.response.TokenResponse;
import com.rentalapp.rentalapi.dto.response.UserResponse;
import com.rentalapp.rentalapi.mapper.UserMapper;
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
    private final UserMapper userMapper;

    AuthService(AuthenticationManager authenticationManager, JWTService jwtService, UserService userService,
            UserRepository userRepository, UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
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

    public UserResponse getAuthenticatedUserResponse(Authentication authentication) {
        return userMapper.entityToResponse(this.getAuthenticatedUser(authentication));
    }

    public User getAuthenticatedUser(Authentication authentication) {
        return userRepository.findByEmail(authentication.getName());
    }
}
