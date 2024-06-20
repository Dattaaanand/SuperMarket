package com.supermarket.repository;

import com.supermarket.entity.Product;
import com.supermarket.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySection(Section section);
    Optional<Product> findByBarcode(String barcode);
    Optional<Product> findByNameIgnoreCase(String name);
    List<Product> findByQuantityLessThan(Integer reorderLevel);
    List<Product> findByIsActiveTrueOrderByNameAsc();
    
    @Query("SELECT p FROM Product p WHERE p.isActive = true AND p.section.id = :sectionId")
    List<Product> findActiveProductsBySection(Long sectionId);
}
