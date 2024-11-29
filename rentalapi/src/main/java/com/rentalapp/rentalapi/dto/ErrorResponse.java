package com.rentalapp.rentalapi.dto;

import lombok.Data;

@Data
public class ErrorResponse {
    private String message;

    public ErrorResponse() {
        this.message = null;
    }

    public ErrorResponse(String message) {
        this.message = message;
    }

}
