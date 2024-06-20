package com.supermarket.controller;

import com.supermarket.dto.ApiResponse;
import com.supermarket.dto.SectionDTO;
import com.supermarket.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sections")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class SectionController {
    
    private final SectionService sectionService;

    @PostMapping
    public ResponseEntity<ApiResponse<SectionDTO>> createSection(@RequestBody SectionDTO dto) {
        try {
            SectionDTO section = sectionService.createSection(dto);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new ApiResponse<>(true, "Section created successfully", section));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SectionDTO>> updateSection(@PathVariable Long id, @RequestBody SectionDTO dto) {
        try {
            SectionDTO section = sectionService.updateSection(id, dto);
            return ResponseEntity.ok(new ApiResponse<>(true, "Section updated successfully", section));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SectionDTO>> getSection(@PathVariable Long id) {
        try {
            SectionDTO section = sectionService.getSectionById(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Section retrieved successfully", section));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse<SectionDTO>> getSectionByName(@PathVariable String name) {
        try {
            SectionDTO section = sectionService.getSectionByName(name);
            return ResponseEntity.ok(new ApiResponse<>(true, "Section retrieved successfully", section));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<SectionDTO>>> getAllSections() {
        try {
            List<SectionDTO> sections = sectionService.getAllSections();
            return ResponseEntity.ok(new ApiResponse<>(true, "Sections retrieved successfully", sections));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteSection(@PathVariable Long id) {
        try {
            sectionService.deleteSection(id);
            return ResponseEntity.ok(new ApiResponse<>(true, "Section deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, e.getMessage(), null));
        }
    }
}
