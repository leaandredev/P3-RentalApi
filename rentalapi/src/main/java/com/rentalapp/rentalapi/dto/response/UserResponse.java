package com.rentalapp.rentalapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
@NoArgsConstructor
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

}
