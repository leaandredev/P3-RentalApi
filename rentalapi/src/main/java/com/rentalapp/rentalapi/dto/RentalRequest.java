package com.rentalapp.rentalapi.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalRequest {

    @Schema(example = "test home 1")
    @NotBlank(message = "le champ name est obligatoire")
    @NotNull(message = "le champ name ne peut pas être null")
    private String name;

    @NotBlank(message = "le champ surface est obligatoire")
    @NotNull(message = "le champ surface ne peut pas être null")
    @Schema(example = "836")
    private String surface;

    @NotBlank(message = "le champ price est obligatoire")
    @NotNull(message = "le champ price ne peut pas être null")
    @Schema(example = "400")
    private String price;

    @Schema(example = "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg")
    private MultipartFile picture;

    @NotBlank(message = "le champ description est obligatoire")
    @NotNull(message = "le champ description ne peut pas être null")
    @Schema(example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum.")
    private String description;

}
