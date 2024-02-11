// ProductServiceImpl.java
package com.jewelleryshopping.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewelleryshopping.dtos.ProductDTO;
import com.jewelleryshopping.entities.Product;
import com.jewelleryshopping.repositories.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
 
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
 
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
 
    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
 
    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElse(null);
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getProductsByCategory(String category) {
        List<Product> products = productRepository.findByCategory(category);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
 
    @Override
    public List<ProductDTO> findProductByPrice(float price) {
        List<Product> products = productRepository.findByPrice(price);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
 
    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product = productRepository.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }
 
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO findByProductId(int productId) {
        return productRepository.findByProductId(productId);
    }

    @Override
    public List<ProductDTO> saveProducts(List<ProductDTO> productDTOs) {
        List<Product> products = productDTOs.stream()
                .map(productDTO -> modelMapper.map(productDTO, Product.class))
                .collect(Collectors.toList());
        products = productRepository.saveAll(products);
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortProductsByPrice() {
        List<Product> products = productRepository.findAllByOrderByProductNameAsc();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> sortProductsByName() {
        // Assuming you have a property named 'name' in your Product entity
        List<Product> products = productRepository.findAllByOrderByProductNameAsc();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
