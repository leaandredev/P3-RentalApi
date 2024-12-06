package com.rentalapp.rentalapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.exception.DuplicateEntryException;
import com.rentalapp.rentalapi.exception.NoEntryFoundException;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/* Service for User treatment. Handle get and create user actions */
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Get the user by his id
     * 
     * @param userId User id
     * @return The User entity
     */
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoEntryFoundException("The user does not exist"));
    }

    /**
     * Get the user by email address
     * 
     * @param email The user email address
     * @return The User entity
     */
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Save a user in database
     * 
     * @param user The User entity to save
     * @return The User entity saved
     */
    public User saveUser(User user) {
        try {
            userRepository.save(user);
            log.info("User saved");

            return user;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("A user already exist with the email address");
        }
    }
}
