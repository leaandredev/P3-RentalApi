package com.rentalapp.rentalapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.RegisterRequest;
import com.rentalapp.rentalapi.dto.UserResponse;
import com.rentalapp.rentalapi.exception.DuplicateEntryException;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    UserService(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse getUserById(Integer id) {
        return new UserResponse(userRepository.findById(id).get());
    }

    public User createUser(RegisterRequest registerRequest) {
        try {

            User user = new User(
                    registerRequest.getEmail(),
                    registerRequest.getName(),
                    passwordEncoder.encode(registerRequest.getPassword()));
            userRepository.save(user);

            log.info("Register successful");
            return user;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("L'utilisateur " + registerRequest.getEmail() + " existe déjà");
        }
    }
}
