package com.rentalapp.rentalapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Data Transfer Object (DTO) representing a user entity.
 * Contains id, email, name, created and updated date formatted
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponse {

    /** Primary key */
    @NonNull
    @Schema(example = "1")
    private Integer id;

    /** User email, used as login for authentication */
    @NonNull
    @Schema(example = "email@exemple.com")
    private String email;

    /** User name (firstName and/or lastName) */
    @NonNull
    @Schema(example = "Prenom NOM")
    private String name;

    /** Creation date, formatted in "yyyy/MM/dd" */
    @NonNull
    @Schema(example = "2024/12/02")
    private String created_at;

    /** Last update date, formatted in "yyyy/MM/dd" */
    @NonNull
    @Schema(example = "2024/12/02")
    private String updated_at;

}
