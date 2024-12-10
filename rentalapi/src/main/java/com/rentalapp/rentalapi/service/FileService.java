package com.rentalapp.rentalapi.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rentalapp.rentalapi.exception.FileNotFoundRuntimeException;
import com.rentalapp.rentalapi.exception.IllegalContentTypeException;

import lombok.extern.slf4j.Slf4j;

/* Service to handle file treatment for rental actions */
@Service
@Slf4j
public class FileService {

    @Value("${app.base-url}")
    private String baseUrl;

    @Value("${app.file-api-route}")
    private String fileApiRoute;

    private final Path uploadDir;

    public FileService(@Value("${app.upload-dir}") String uploadDir) {
        this.uploadDir = Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    /**
     * Save picture in local directory and return an accessible URL
     * 
     * @param file Picture provided to represent the rental
     * @return The absolut file URL of the picture
     */
    @SuppressWarnings("null")
    public String savePicture(MultipartFile file) {
        if (!file.getContentType().startsWith("image/")) {
            throw new IllegalContentTypeException("File is not a picture");
        } else {
            return saveFile(file);
        }
    }

    /**
     * Save the file in local directory and return an accessible URL
     * app
     * 
     * @param file File provided
     * @return The absolut file URL
     */
    public String saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }

        try {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadDir.resolve(fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, file.getBytes());

            return baseUrl + fileApiRoute + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file", e);
        }
    }

    /**
     * Generate a Picture resource to represent a rental picture from a filename
     * 
     * @param filename filename of the rental picture
     * @return a Picture resource to represent rental picture
     */
    public Resource getRentalPicture(String filename) {
        return getFile(filename);
    }

    /**
     * Generate a File resource from a filename
     * 
     * @param filename
     * @return a FileResource
     */
    public Resource getFile(String filename) {
        try {
            Path filePath = uploadDir.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() && resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundRuntimeException("File doesn't exist or isn't readable");
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundRuntimeException("File not found : URL Malformed");
        }
    }
}
