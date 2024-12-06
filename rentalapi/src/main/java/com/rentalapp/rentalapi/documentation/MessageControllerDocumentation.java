package com.rentalapp.rentalapi.documentation;

import org.springframework.http.ResponseEntity;

import com.rentalapp.rentalapi.dto.request.MessageRequest;
import com.rentalapp.rentalapi.dto.response.ErrorResponse;
import com.rentalapp.rentalapi.dto.response.OkResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Messages", description = "Operations related to messages")
public interface MessageControllerDocumentation {

    @Operation(summary = "Send Message", description = "Send a message to a rental owner")
    @ApiResponse(responseCode = "200", description = "Message send successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OkResponse.class)))
    @ApiResponse(responseCode = "401", description = "Invalid credentials, access denied", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    public abstract ResponseEntity<OkResponse> sendMessage(MessageRequest messageRequest);

}
