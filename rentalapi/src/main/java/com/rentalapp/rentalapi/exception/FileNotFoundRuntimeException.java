package com.rentalapp.rentalapi.exception;

public class FileNotFoundRuntimeException extends RuntimeException {
    public FileNotFoundRuntimeException(String message) {
        super(message);
    }
}
