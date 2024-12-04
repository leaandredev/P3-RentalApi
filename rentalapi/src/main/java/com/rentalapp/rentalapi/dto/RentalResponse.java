package com.rentalapp.rentalapi.dto;

import java.time.format.DateTimeFormatter;

import com.rentalapp.rentalapi.model.Rental;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NonNull;

@Data
public class RentalResponse {
    @NonNull
    @Schema(example = "1")
    private Integer id;

    @Schema(example = "test house 1")
    private String name;

    @Schema(example = "432")
    @Column(precision = 10, scale = 0)
    private Integer surface;

    @Schema(example = "300")
    @Column(precision = 10, scale = 0)
    private Integer price;

    @Schema(example = "https://blog.technavio.org/wp-content/uploads/2018/12/Online-House-Rental-Sites.jpg")
    private String picture;

    @Schema(example = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam a lectus eleifend, varius massa ac, mollis tortor. Quisque ipsum nulla, faucibus ac metus a, eleifend efficitur augue. Integer vel pulvinar ipsum.")
    private String description;

    @NonNull
    @Schema(example = "1")
    private Integer owner_id;

    @NonNull
    @Schema(example = "2024/12/02")
    private String created_at;

    @NonNull
    @Schema(example = "2024/12/02")
    private String updated_at;

    public RentalResponse(Rental rental) {
        this.id = rental.getId();
        this.name = rental.getName();
        this.surface = rental.getSurface();
        this.price = rental.getPrice();
        this.picture = rental.getPicture();
        this.description = rental.getDescription();
        this.owner_id = rental.getOwner().getId();
        this.created_at = rental.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        this.updated_at = rental.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

}
