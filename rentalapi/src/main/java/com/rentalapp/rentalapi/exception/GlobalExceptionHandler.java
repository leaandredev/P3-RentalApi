package com.rentalapp.rentalapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rentalapp.rentalapi.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ AccessDeniedException.class, UnauthorizedException.class, AuthenticationException.class })
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(Exception exception) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(new ErrorResponse("Accès non authorisé"));
    }

    @ExceptionHandler({ HttpMessageNotReadableException.class, DuplicateEntryException.class })
    public ResponseEntity<ErrorResponse> handleBadequest(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new ErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException exception) {
        String errors = "";
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors += error.getDefaultMessage() + ". ";
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception e) {
        System.err.println("Error: " + e.getClass().getName() + " - " + e.getMessage());
        e.printStackTrace();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(new ErrorResponse(e.getMessage()));
    }
}
