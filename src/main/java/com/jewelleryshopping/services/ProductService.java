// ProductService.java
package com.jewelleryshopping.services;

import java.util.List;

import com.jewelleryshopping.dtos.ProductDTO;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    List<ProductDTO> getProductsByCategory(String category);
    List<ProductDTO> findProductByPrice(float price);
    ProductDTO saveProduct(ProductDTO productDTO);
    void deleteProduct(Long id);
    ProductDTO findByProductId(int productId);
    List<ProductDTO> saveProducts(List<ProductDTO> productDTOs); // New method for saving multiple products
    List<ProductDTO> sortProductsByPrice();
    List<ProductDTO> sortProductsByName();
}
