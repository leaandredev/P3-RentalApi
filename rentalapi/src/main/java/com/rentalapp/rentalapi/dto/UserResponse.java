package com.rentalapp.rentalapi.dto;

import java.time.format.DateTimeFormatter;

import com.rentalapp.rentalapi.model.DbUser;

import lombok.Data;
import lombok.NonNull;

@Data
public class UserResponse {
    @NonNull
    private Integer id;

    @NonNull
    private String email;

    @NonNull
    private String name;

    @NonNull
    private String created_at;

    @NonNull
    private String updated_at;

    public UserResponse(DbUser dbUser) {
        this.id = dbUser.getId();
        this.email = dbUser.getEmail();
        this.name = dbUser.getName();
        this.created_at = dbUser.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.updated_at = dbUser.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
