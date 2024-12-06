package com.rentalapp.rentalapi.documentation;

import org.springframework.http.ResponseEntity;

import com.rentalapp.rentalapi.dto.response.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Users", description = "Operations related to users")
public interface UserControllerDocumentation {

    @Operation(summary = "Get User", description = "Get user details")
    @ApiResponse(responseCode = "200", description = "User successfully retrieved", content = @Content(schema = @Schema(implementation = UserResponse.class)))
    public abstract ResponseEntity<UserResponse> getUser(
            @Parameter(description = "User ID", required = true) String id);

}
