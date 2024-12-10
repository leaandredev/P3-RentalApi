package com.rentalapp.rentalapi.documentation;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "File", description = "endpoints for files treatments")
public interface FileControllerDocumentation {

    /**
     * Get the rental illustration, stored in a local directory
     * 
     * @param filename rental picture filename, as an URL parameter
     * @return the rental picture in a Http response
     */
    @Operation(summary = "Get Rental Picture", description = "Get the rental illustration, stored in a local directory")
    @ApiResponse(responseCode = "200", description = "Picture retrieved successfully")
    public abstract ResponseEntity<Resource> getRentalPicture(@PathVariable String filename);
}
