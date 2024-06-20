package com.supermarket.service;

import com.supermarket.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO createProduct(ProductDTO dto);
    ProductDTO updateProduct(Long id, ProductDTO dto);
    ProductDTO getProductById(Long id);
    ProductDTO getProductByBarcode(String barcode);
    List<ProductDTO> getAllProducts();
    List<ProductDTO> getProductsBySection(Long sectionId);
    List<ProductDTO> getLowStockProducts();
    void deleteProduct(Long id);
    void activateProduct(Long id);
    void deactivateProduct(Long id);
    void updateProductQuantity(Long productId, Integer newQuantity, String reason);
}
