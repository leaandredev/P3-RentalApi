package com.rentalapp.rentalapi.dto.request;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * Data Transfer Object (DTO) for handling rental property creation requests.
 * Contains the necessary details about a rental property, including its name,
 * surface area, price, picture, and description.
 */
@Data
public class RentalRequest {

    /**
     * The name of the rental property.
     * This field is mandatory and cannot be null or blank.
     */
    @NotBlank(message = "The name field is required.")
    @NotNull(message = "The name field cannot be null.")
    @Schema(description = "Name of the rental property.", example = "Test Home 1")
    private String name;

    /**
     * The surface area of the rental property (m2).
     * This field is mandatory and cannot be null or blank.
     */
    @NotBlank(message = "The surface field is required.")
    @NotNull(message = "The surface field cannot be null.")
    @Schema(description = "Surface area of the rental property in square meters.", example = "836")
    private String surface;

    /**
     * The price of the rental property (per night).
     * This field is mandatory and cannot be null or blank.
     */
    @NotBlank(message = "The price field is required.")
    @NotNull(message = "The price field cannot be null.")
    @Schema(description = "Price of the rental property per night.", example = "400")
    private String price;

    /**
     * A picture of the rental property to serve as an illustration.
     * This field is optional.
     */
    @Schema(description = "Picture to illustrate the rental property.", example = "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg")
    private MultipartFile picture;

    /**
     * A description of the rental property.
     * This field is mandatory and cannot be null or blank.
     */
    @NotBlank(message = "The description field is required.")
    @NotNull(message = "The description field cannot be null.")
    @Schema(description = "Detailed description of the rental property.", example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue.")
    private String description;
}
