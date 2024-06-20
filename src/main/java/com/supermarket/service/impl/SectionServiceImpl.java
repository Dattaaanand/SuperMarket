package com.supermarket.service.impl;

import com.supermarket.dto.SectionDTO;
import com.supermarket.entity.Section;
import com.supermarket.exception.DuplicateResourceException;
import com.supermarket.exception.ResourceNotFoundException;
import com.supermarket.repository.SectionRepository;
import com.supermarket.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {
    
    private final SectionRepository sectionRepository;

    @Override
    public SectionDTO createSection(SectionDTO dto) {
        if (sectionRepository.findByNameIgnoreCase(dto.getName()).isPresent()) {
            throw new DuplicateResourceException("Section with name '" + dto.getName() + "' already exists");
        }

        Section section = Section.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .build();

        Section saved = sectionRepository.save(section);
        return mapToDTO(saved);
    }

    @Override
    public SectionDTO updateSection(Long id, SectionDTO dto) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + id));

        section.setName(dto.getName());
        section.setDescription(dto.getDescription());

        Section updated = sectionRepository.save(section);
        return mapToDTO(updated);
    }

    @Override
    public SectionDTO getSectionById(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + id));
        return mapToDTO(section);
    }

    @Override
    public SectionDTO getSectionByName(String name) {
        Section section = sectionRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with name: " + name));
        return mapToDTO(section);
    }

    @Override
    public List<SectionDTO> getAllSections() {
        return sectionRepository.findAllByOrderByNameAsc()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteSection(Long id) {
        Section section = sectionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + id));
        sectionRepository.delete(section);
    }

    private SectionDTO mapToDTO(Section section) {
        return SectionDTO.builder()
                .id(section.getId())
                .name(section.getName())
                .description(section.getDescription())
                .productCount(section.getProducts() != null ? section.getProducts().size() : 0)
                .createdAt(section.getCreatedAt())
                .updatedAt(section.getUpdatedAt())
                .build();
    }
}
