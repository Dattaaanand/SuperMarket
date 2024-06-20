package com.supermarket.repository;

import com.supermarket.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByNameIgnoreCase(String name);
    List<Section> findAllByOrderByNameAsc();
}
