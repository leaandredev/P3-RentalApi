package com.rentalapp.rentalapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.RegisterRequest;
import com.rentalapp.rentalapi.exception.DuplicateEntryException;
import com.rentalapp.rentalapi.model.User;
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

    public User createUser(RegisterRequest registerRequest) {
        try {

            User user = new User(
                    registerRequest.getEmail(),
                    registerRequest.getName(),
                    passwordEncoder.encode(registerRequest.getPassword()));
            userRepository.save(user);

            System.out.println("Register successful");
            return user;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("L'utilisateur " + registerRequest.getEmail() + " existe déjà");
        }
    }
}
