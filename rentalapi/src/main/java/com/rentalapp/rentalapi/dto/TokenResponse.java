package com.rentalapp.rentalapi.dto;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor

public class TokenResponse {

    @NonNull
    private String token;

}
