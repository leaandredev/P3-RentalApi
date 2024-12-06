package com.rentalapp.rentalapi.mapper;

import java.time.format.DateTimeFormatter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rentalapp.rentalapi.dto.request.RegisterRequest;
import com.rentalapp.rentalapi.dto.response.UserResponse;
import com.rentalapp.rentalapi.model.User;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User requestToEntity(RegisterRequest request) {
        return new User(
                null,
                request.getEmail(),
                request.getName(),
                passwordEncoder.encode(request.getPassword()),
                null,
                null);
    }

    public UserResponse entityToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                user.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

}
