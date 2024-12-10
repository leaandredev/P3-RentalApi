package com.rentalapp.rentalapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


/**
 * Data Transfer Object (DTO) representing a rental entity.
 * Contains id, name, surface, price, picture, description, the id of the rental
 * owner, created and updated date formatted
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RentalResponse {

    /** Primary key */
    @NonNull
    @Schema(example = "1")
    private Integer id;

    /** Name of the rental property */
    @Schema(example = "test house 1")
    private String name;

    /** Surface area (m2) */
    @Schema(example = "432")
    @Column(precision = 10, scale = 0)
    private Integer surface;

    /** Rental price per night */
    @Schema(example = "300")
    @Column(precision = 10, scale = 0)
    private Integer price;

    /** Absolut URL to the property picture */
    @Schema(example = "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg")
    private String picture;

    /** Rental property description */
    @Schema(example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum.")
    private String description;

    /** Id of the rental owner */
    @NonNull
    @Schema(example = "1")
    private Integer owner_id;
    
    /** Creation date, formatted in "yyyy/MM/dd" */
    @NonNull
    @Schema(example = "2024/12/02")
    private String created_at;
    
    /** Last update date, formatted in "yyyy/MM/dd" */
    @NonNull
    @Schema(example = "2024/12/02")
    private String updated_at;

}
