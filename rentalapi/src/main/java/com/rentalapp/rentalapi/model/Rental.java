package com.rentalapp.rentalapi.model;

import java.time.LocalDateTime;

import com.rentalapp.rentalapi.dto.RentalRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(precision = 10, scale = 0)
    private Integer surface;

    @Column(precision = 10, scale = 0)
    private Integer price;

    private String picture;
    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Rental() {
    }

    public Rental(User owner, RentalRequest rentalRequest) {
        this.name = rentalRequest.getName();
        this.surface = Integer.valueOf(rentalRequest.getSurface());
        this.price = Integer.valueOf(rentalRequest.getPrice());
        this.picture = rentalRequest.getPicture().getOriginalFilename();
        this.description = rentalRequest.getDescription();
        this.owner = owner;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

}
