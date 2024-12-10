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
     * Maps a {@link RegisterRequest} to a {@link User} entity using User builder
     * 
     * @param request the register request containing the input data.
     * @return a new {@link User} entity populated with the register request datas
     */
    public User requestToEntity(RegisterRequest request) {
        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
    }

    /**
     * Maps a {@link User} entity to a {@link UserResponse} using UserResponse
     * builder
     * 
     * @param user the user entity to convert
     * @return a new {@link UserResponse} containing the user's data
     */
    public UserResponse entityToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .created_at(user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .updated_at(user.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .build();
    }

}
