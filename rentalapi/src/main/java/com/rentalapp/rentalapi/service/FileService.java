package com.rentalapp.rentalapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/* Service to handle file treatment for rental actions */
@Service
public class FileService {

    @Value("${app.base-url}")
    private String baseUrl;

    /**
     * Save the file in local directory and return an accessible URL for the front
     * app
     * 
     * @param file Picture provided to represent the rental
     * @return The absolut file URL of the picture
     */
    public String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }

        try {
            String uploadDir = "src/main/resources/static/uploads/";
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            return baseUrl + "/uploads/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }
}
