package com.rentalapp.rentalapi.mapper;

import java.time.format.DateTimeFormatter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rentalapp.rentalapi.dto.request.RegisterRequest;
import com.rentalapp.rentalapi.dto.response.UserResponse;
import com.rentalapp.rentalapi.model.User;

/**
 * Mapper component for converting between User entities, DTOs, and requests.
 */
@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Maps a {@link RegisterRequest} to a {@link User} entity
     * 
     * @param request the register request containing the input data.
     * @return a new {@link User} entity populated with the register request datas
     */
    public User requestToEntity(RegisterRequest request) {
        return new User(
                null,
                request.getEmail(),
                request.getName(),
                passwordEncoder.encode(request.getPassword()),
                null,
                null);
    }

    /**
     * Maps a {@link User} entity to a {@link UserResponse}
     * 
     * @param user the user entity to convert
     * @return a new {@link UserResponse} containing the user's data
     */
    public UserResponse entityToResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                user.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

}
