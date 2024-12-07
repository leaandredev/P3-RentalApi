package com.rentalapp.rentalapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for handling message creation requests.
 * Includes the message content, the user ID, and the rental ID.
 */
@Data
public class MessageRequest {

    /**
     * The content of the message sent by the user.
     * This field is mandatory and cannot be null or blank.
     */
    @Schema(example = "Mon message")
    @NotBlank(message = "The message field is required.")
    @NotNull(message = "The message field cannot be null.")
    private String message;

    /**
     * The ID of the rental owner
     * This field is mandatory and cannot be null.
     */
    @Schema(example = "2")
    @NotNull(message = "The user_id field cannot be null.")
    private Integer user_id;

    /**
     * The ID of the rental associated with the message.
     * This field is mandatory and cannot be null.
     */
    @Schema(example = "5")
    @NotNull(message = "The rental_id field cannot be null.")
    private Integer rental_id;
}
