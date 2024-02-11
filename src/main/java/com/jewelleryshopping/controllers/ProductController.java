package com.jewelleryshopping.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jewelleryshopping.dtos.ProductDTO;
import com.jewelleryshopping.services.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {
 
    private final ProductService productService;
 
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
 
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        ProductDTO product = productService.getProductById(id);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO) {
        ProductDTO savedProduct = productService.saveProduct(productDTO);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") Long id, @Valid @RequestBody ProductDTO productDTO) {
        ProductDTO existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            productDTO.setProductId(id);
            ProductDTO updatedProduct = productService.saveProduct(productDTO);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        ProductDTO existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/category/{category}")
    public List<ProductDTO> getProductsByCategory(@PathVariable String category) {
        return productService.getProductsByCategory(category);
    }
 
    @GetMapping("/price/{price}")
    public List<ProductDTO> findProductByPrice(@PathVariable float price) {
        return productService.findProductByPrice(price);
    }
    
    @GetMapping("/sort/price")
    public ResponseEntity<List<ProductDTO>> sortProductsByPrice() {
        List<ProductDTO> sortedProducts = productService.sortProductsByPrice();
        return new ResponseEntity<>(sortedProducts, HttpStatus.OK);
    }
 
    @GetMapping("/sort/name")
    public ResponseEntity<List<ProductDTO>> sortProductsByName() {
        List<ProductDTO> sortedProducts = productService.sortProductsByName();
        return new ResponseEntity<>(sortedProducts, HttpStatus.OK);
    }
}
