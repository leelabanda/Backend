package com.spring.main.excel;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.spring.main.exceliservice.ExcelUploadService;

import java.io.IOException;

@RestController
@RequestMapping("/api/persons")
@RequiredArgsConstructor
public class ExcelController {

    private final ExcelUploadService excelUploadService;

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<String> uploadExcel(
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload an Excel file.");
        }

        try {
            excelUploadService.uploadExcel(file);
            return ResponseEntity.ok("Excel uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading Excel file: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading data: " + e.getMessage());
        }
    }
}