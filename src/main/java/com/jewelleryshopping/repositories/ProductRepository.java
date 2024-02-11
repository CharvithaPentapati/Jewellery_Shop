package com.jewelleryshopping.repositories;

import com.jewelleryshopping.dtos.ProductDTO;
import com.jewelleryshopping.entities.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByCategory(String category);

    List<Product> findByPrice(float price);

    // Sorting methods
    List<Product> findAllByOrderByProductNameAsc(); // Assuming 'productName' is the property for the name

    // Or, you can add a 'name' property to your Product entity and use it for sorting
    // List<Product> findAllByOrderByNameAsc(); // Make sure 'name' is a valid property in your Product entity
    ProductDTO findByProductId(int productId);
}
