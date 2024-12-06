package com.rentalapp.rentalapi.mapper;

import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

import com.rentalapp.rentalapi.dto.request.RentalRequest;
import com.rentalapp.rentalapi.dto.response.RentalResponse;
import com.rentalapp.rentalapi.model.Rental;
import com.rentalapp.rentalapi.model.User;
import com.rentalapp.rentalapi.service.FileService;

@Component
public class RentalMapper {

    private final FileService fileService;

    public RentalMapper(FileService fileService) {
        this.fileService = fileService;
    }

    public Rental requestToEntity(RentalRequest request, User owner) {
        return new Rental(
                null,
                request.getName(),
                Integer.valueOf(request.getSurface()),
                Integer.valueOf(request.getPrice()),
                fileService.saveFile(request.getPicture()),
                request.getDescription(),
                owner,
                null,
                null);
    }

    public Rental updateEntityFromRequest(Rental rental, RentalRequest rentalRequest) {
        rental.setName(rentalRequest.getName());
        rental.setPrice(Integer.valueOf(rentalRequest.getPrice()));
        rental.setSurface(Integer.valueOf(rentalRequest.getSurface()));
        rental.setDescription(rentalRequest.getDescription());
        return rental;
    }

    public RentalResponse entityToResponse(Rental rental) {
        return new RentalResponse(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                rental.getPicture(),
                rental.getDescription(),
                rental.getOwner().getId(),
                rental.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")),
                rental.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

    }

}
