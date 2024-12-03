package com.rentalapp.rentalapi.dto;

import java.time.format.DateTimeFormatter;

import com.rentalapp.rentalapi.model.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserResponse {
    @NonNull
    @Schema(example = "1")
    private Integer id;

    @NonNull
    @Schema(example = "email@exemple.com")
    private String email;

    @NonNull
    @Schema(example = "Prenom NOM")
    private String name;

    @NonNull
    @Schema(example = "2024/12/02")
    private String created_at;

    @NonNull
    @Schema(example = "2024/12/02")
    private String updated_at;

    public UserResponse(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.created_at = user.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.updated_at = user.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
