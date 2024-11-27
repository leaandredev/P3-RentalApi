package com.rentalapp.rentalapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rentalapp.rentalapi.model.TestEntity;
import com.rentalapp.rentalapi.repository.TestEntityRepository;

@Component
public class TestDatabaseConnection implements CommandLineRunner {

    @Autowired
    private TestEntityRepository repository;

    @Override
    public void run(String... args) throws Exception {
        TestEntity entity = new TestEntity();
        entity.setId(1L);
        entity.setName("Test Entity");

        repository.save(entity);

        System.out.println("Donnée sauvegardée dans la base de données !");
        System.out.println("Données : " + repository.findAll());
    }
}
