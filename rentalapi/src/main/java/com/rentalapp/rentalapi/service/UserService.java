package com.rentalapp.rentalapi.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.RegisterRequest;
import com.rentalapp.rentalapi.model.DbUser;
import com.rentalapp.rentalapi.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
