package com.rentalapp.rentalapi.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.rentalapp.rentalapi.dto.request.RegisterRequest;
import com.rentalapp.rentalapi.dto.response.UserResponse;
import com.rentalapp.rentalapi.exception.DuplicateEntryException;
import com.rentalapp.rentalapi.mapper.UserMapper;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserResponse getUserById(Integer id) {
        return userMapper.entityToResponse(userRepository.findById(id).get());
    }

    public User createUser(RegisterRequest registerRequest) {
        try {
            User user = userMapper.requestToEntity(registerRequest);
            userRepository.save(user);

            log.info("Register successful");
            return user;
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("L'utilisateur " + registerRequest.getEmail() + " existe déjà");
        }
    }
}
