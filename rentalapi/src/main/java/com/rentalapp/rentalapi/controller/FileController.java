package com.rentalapp.rentalapi.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rentalapp.rentalapi.documentation.FileControllerDocumentation;
import com.rentalapp.rentalapi.service.FileService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("${app.file-api-route}")
public class FileController implements FileControllerDocumentation {

    private final FileService fileService;

    FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    @GetMapping("{filename}")
    public ResponseEntity<Resource> getRentalPicture(@PathVariable String filename) {
        Resource picture = fileService.getFile(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + picture.getFilename() + "\"")
                .header(HttpHeaders.CONTENT_TYPE, "image/*")
                .body(picture);
    }
}
