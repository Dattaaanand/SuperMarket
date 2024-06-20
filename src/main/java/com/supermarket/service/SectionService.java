package com.supermarket.service;

import com.supermarket.dto.SectionDTO;
import java.util.List;

public interface SectionService {
    SectionDTO createSection(SectionDTO dto);
    SectionDTO updateSection(Long id, SectionDTO dto);
    SectionDTO getSectionById(Long id);
    SectionDTO getSectionByName(String name);
    List<SectionDTO> getAllSections();
    void deleteSection(Long id);
}
