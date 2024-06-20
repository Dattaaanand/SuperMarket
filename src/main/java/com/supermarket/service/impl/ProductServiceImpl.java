package com.supermarket.service.impl;

import com.supermarket.dto.ProductDTO;
import com.supermarket.entity.Product;
import com.supermarket.entity.Section;
import com.supermarket.entity.InventoryLog;
import com.supermarket.exception.ResourceNotFoundException;
import com.supermarket.repository.ProductRepository;
import com.supermarket.repository.SectionRepository;
import com.supermarket.repository.InventoryLogRepository;
import com.supermarket.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    
    private final ProductRepository productRepository;
    private final SectionRepository sectionRepository;
    private final InventoryLogRepository inventoryLogRepository;

    @Override
    public ProductDTO createProduct(ProductDTO dto) {
        Section section = sectionRepository.findById(dto.getSectionId())
                .orElseThrow(() -> new ResourceNotFoundException("Section not found with id: " + dto.getSectionId()));

        Product product = Product.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .reorderLevel(dto.getReorderLevel() != null ? dto.getReorderLevel() : 10)
                .barcode(dto.getBarcode())
                .section(section)
                .isActive(true)
                .build();

        Product saved = productRepository.save(product);
        return mapToDTO(saved);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setReorderLevel(dto.getReorderLevel());
        product.setBarcode(dto.getBarcode());

        Product updated = productRepository.save(product);
        return mapToDTO(updated);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        return mapToDTO(product);
    }

    @Override
    public ProductDTO getProductByBarcode(String barcode) {
        Product product = productRepository.findByBarcode(barcode)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with barcode: " + barcode));
        return mapToDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findByIsActiveTrueOrderByNameAsc()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getProductsBySection(Long sectionId) {
        return productRepository.findActiveProductsBySection(sectionId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> getLowStockProducts() {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getQuantity() < p.getReorderLevel())
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    @Override
    public void activateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        product.setIsActive(true);
        productRepository.save(product);
    }

    @Override
    public void deactivateProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        product.setIsActive(false);
        productRepository.save(product);
    }

    @Override
    public void updateProductQuantity(Long productId, Integer newQuantity, String reason) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + productId));

        Integer oldQuantity = product.getQuantity();
        Integer change = newQuantity - oldQuantity;

        product.setQuantity(newQuantity);
        productRepository.save(product);

        // Log the inventory change
        InventoryLog log = InventoryLog.builder()
                .product(product)
                .quantityChange(change)
                .reason(reason)
                .build();
        inventoryLogRepository.save(log);
    }

    private ProductDTO mapToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .reorderLevel(product.getReorderLevel())
                .barcode(product.getBarcode())
                .sectionId(product.getSection().getId())
                .sectionName(product.getSection().getName())
                .isActive(product.getIsActive())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
