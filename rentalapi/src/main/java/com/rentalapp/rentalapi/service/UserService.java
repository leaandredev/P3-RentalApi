package com.rentalapp.rentalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.RegisterRequest;
import com.rentalapp.rentalapi.dto.TokenResponse;
import com.rentalapp.rentalapi.model.DbUser;
import com.rentalapp.rentalapi.repository.UserRepository;

@Service
public class UserService {

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    UserService(AuthenticationManager authenticationManager, JWTService jwtService, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenResponse authentificateUser(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password));
        System.out.println("Authentication successful");

        return new TokenResponse(jwtService.generateToken(authentication));
    }

    public DbUser createUser(RegisterRequest registerRequest) {
        DbUser dbUser = new DbUser();
        dbUser.setEmail(registerRequest.getEmail());
        dbUser.setName(registerRequest.getName());
        dbUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(dbUser);

        System.out.println("Register successful");
        return dbUser;
    }
}
