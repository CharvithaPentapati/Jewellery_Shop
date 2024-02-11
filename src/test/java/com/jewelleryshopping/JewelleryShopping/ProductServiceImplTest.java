
package com.jewelleryshopping.JewelleryShopping;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;

import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.modelmapper.ModelMapper;

import com.jewelleryshopping.dtos.ProductDTO;

import com.jewelleryshopping.entities.Product;

import com.jewelleryshopping.repositories.ProductRepository;

import com.jewelleryshopping.services.ProductServiceImpl;

public class ProductServiceImplTest {

    private ProductServiceImpl productService;

    private ProductRepository productRepository;

    private ModelMapper modelMapper;

    @BeforeEach

    void setUp() {

        productRepository = mock(ProductRepository.class);

        modelMapper = new ModelMapper();

        productService = new ProductServiceImpl(productRepository, modelMapper);

    }

    @Test

    void testGetAllProducts() {

        // Given

        List<Product> products = new ArrayList<>();

        products.add(new Product());

        products.add(new Product());

        when(productRepository.findAll()).thenReturn(products);

        // When

        List<ProductDTO> productDTOs = productService.getAllProducts();

        // Then

        assertEquals(products.size(), productDTOs.size());

    }

    @Test

    void testGetProductById() {

        // Given

        Long id = 1L;

        Product product = new Product();

        product.setproductId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // When

        ProductDTO productDTO = productService.getProductById(id);

        // Then

        assertNotNull(productDTO);

        assertEquals(id, productDTO.getProductId());

    }

    @Test

    void testGetProductsByCategory() {

        // Given

        String category = "Jewelry";

        List<Product> products = new ArrayList<>();

        products.add(new Product());

        products.add(new Product());

        when(productRepository.findByCategory(category)).thenReturn(products);

        // When

        List<ProductDTO> productDTOs = productService.getProductsByCategory(category);

        // Then

        assertEquals(products.size(), productDTOs.size());

    }

    @Test

    void testFindProductByPrice() {

        // Given

        float price = 50.0f;

        List<Product> products = new ArrayList<>();

        products.add(new Product());

        products.add(new Product());

        when(productRepository.findByPrice(price)).thenReturn(products);

        // When

        List<ProductDTO> productDTOs = productService.findProductByPrice(price);

        // Then

        assertEquals(products.size(), productDTOs.size());

    }

    @Test

    void testDeleteProduct() {

        // Given

        Long id = 1L;

        // When

        productService.deleteProduct(id);

        // Then

        verify(productRepository, times(1)).deleteById(id);

    }

    @Test

    void testFindByProductId() {

        // Given

        int productId = 1;

        ProductDTO productDTO = new ProductDTO();

        productDTO.setProductId((long) productId);

        when(productRepository.findByProductId(productId)).thenReturn(productDTO);

        // When

        ProductDTO retrievedProductDTO = productService.findByProductId(productId);

        // Then

        assertNotNull(retrievedProductDTO);

        assertEquals(productId, retrievedProductDTO.getProductId().intValue());

    }

}
