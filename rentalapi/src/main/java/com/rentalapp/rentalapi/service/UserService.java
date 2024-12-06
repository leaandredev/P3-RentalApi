package com.rentalapp.rentalapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.exception.DuplicateEntryException;
import com.rentalapp.rentalapi.exception.NoEntryFoundException;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoEntryFoundException("L'utilisateur avec l'id " + userId + " n'existe pas."));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        try {
            userRepository.save(user);

            log.info("user saved");
            return user;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("L'utilisateur " + user.getEmail() + " existe déjà");
        }
    }
}
