package com.rentalapp.rentalapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rentalapp.rentalapi.model.TestEntity;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {

}
