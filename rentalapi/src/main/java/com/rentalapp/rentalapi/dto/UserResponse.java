package com.rentalapp.rentalapi.dto;

import java.time.format.DateTimeFormatter;

import com.rentalapp.rentalapi.model.DbUser;

import lombok.Data;

@Data
public class UserResponse {
    private Integer id;
    private String email;
    private String name;
    private String created_at;
    private String updated_at;

    public UserResponse(DbUser dbUser) {
        this.id = dbUser.getId();
        this.email = dbUser.getEmail();
        this.name = dbUser.getName();
        this.created_at = dbUser.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.updated_at = dbUser.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
