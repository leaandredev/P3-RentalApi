package com.rentalapp.rentalapi.dto;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RentalRequest {

    @Schema(example = "test home 1")
    @NotBlank(message = "le champ email est obligatoire")
    @NotNull(message = "le champ email ne peut pas être null")
    private String name;

    @Schema(example = "836")
    private String surface;

    @Schema(example = "400")
    private String price;

    @Schema(example = "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg")
    private MultipartFile picture;

    @Schema(example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum.")
    private String description;

}
