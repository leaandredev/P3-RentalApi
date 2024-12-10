package com.rentalapp.rentalapi.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.rentalapp.rentalapi.dto.request.RentalRequest;
import com.rentalapp.rentalapi.dto.response.RentalResponse;
import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.service.FileService;

/**
 * Mapper component for converting between Rental entities, DTOs, and requests.
 */
@Component
public class RentalMapper {

    private final FileService fileService;

    public RentalMapper(FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * Maps a {@link RentalRequest} to a {@link Rental} entity using Rental builder
     *
     * @param request the rental request containing the input data.
     * @param owner   the owner of the rental, represented as a {@link User}.
     * @return a new {@link Rental} entity populated with the data from the request.
     */
    public Rental requestToEntity(RentalRequest request, User owner) {
        return Rental.builder()
                .name(request.getName())
                .surface(Integer.valueOf(request.getSurface()))
                .price(Integer.valueOf(request.getPrice()))
                .picture(fileService.saveFile(request.getPicture()))
                .description(request.getDescription())
                .owner(owner)
                .build();
    }

    /**
     * Updates an existing {@link Rental} entity with the data from a
     * {@link RentalRequest}.
     *
     * @param rental        the existing rental entity to update.
     * @param rentalRequest the rental request containing the new data.
     * @return the updated {@link Rental} entity.
     */
    public Rental updateEntityFromRequest(Rental rental, RentalRequest rentalRequest) {
        rental.setName(rentalRequest.getName());
        rental.setPrice(Integer.valueOf(rentalRequest.getPrice()));
        rental.setSurface(Integer.valueOf(rentalRequest.getSurface()));
        rental.setDescription(rentalRequest.getDescription());
        rental.setUpdatedAt(LocalDateTime.now());
        return rental;
    }

    /**
     * Maps a {@link Rental} entity to a {@link RentalResponse} using Rental builder
     *
     * @param rental the rental entity to convert.
     * @return a new {@link RentalResponse} containing the rental's data.
     */
    public RentalResponse entityToResponse(Rental rental) {
        return RentalResponse.builder()
                .id(rental.getId())
                .name(rental.getName())
                .surface(rental.getSurface())
                .price(rental.getPrice())
                .picture(rental.getPicture())
                .description(rental.getDescription())
                .owner_id(rental.getOwner().getId())
                .created_at(rental.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .updated_at(rental.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")))
                .build();
    }

}
