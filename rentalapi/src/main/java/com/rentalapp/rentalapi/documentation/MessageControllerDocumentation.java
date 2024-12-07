package com.rentalapp.rentalapi.documentation;

import org.springframework.http.ResponseEntity;

import com.rentalapp.rentalapi.dto.request.MessageRequest;
import com.rentalapp.rentalapi.dto.response.SuccessResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/** MessageController Annotations for OpenAPI documentation */
@Tag(name = "Messages", description = "Endpoints related to messages between user and rental owners")
public interface MessageControllerDocumentation {

    /**
     * Send a message to a rental owner
     * 
     * @param messageRequest A requestBody with message details for owner rental
     * @return a success message if message successfully saved
     */
    @Operation(summary = "Send Message", description = "Send a message to a rental owner")
    @ApiResponse(responseCode = "200", description = "Message send successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = SuccessResponse.class)))
    public abstract ResponseEntity<SuccessResponse> sendMessage(MessageRequest messageRequest);

}
