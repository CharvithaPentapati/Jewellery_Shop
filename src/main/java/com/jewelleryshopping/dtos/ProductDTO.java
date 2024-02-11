package com.jewelleryshopping.dtos;
 
import jakarta.validation.constraints.NotBlank;
 
import jakarta.validation.constraints.Positive;
 
import jakarta.validation.constraints.PositiveOrZero;
public class ProductDTO {
 
    private Long productId;
    @NotBlank(message = "Product name is required")
 
    private String productName;
    private String category;
    @PositiveOrZero(message = "Quantity must be positive or zero")
 
    private int quantity;
    @Positive(message = "Price must be positive")
 
    private float price;
    // Constructors
 
    public ProductDTO() {
 
    }
    // Getters and setters
 
    public Long getProductId() {
 
        return productId;
 
    }
    public void setProductId(Long productId) {
 
        this.productId = productId;
 
    }
    public String getProductName() {
 
        return productName;
 
    }
    public void setProductName(String productName) {
 
        this.productName = productName;
 
    }
    public String getCategory() {
 
        return category;
 
    }
    public void setCategory(String category) {
 
        this.category = category;
 
    }
    public int getQuantity() {
 
        return quantity;
 
    }
    public void setQuantity(int quantity) {
 
        this.quantity = quantity;
 
    }
    public float getPrice() {
 
        return price;
 
    }
    public void setPrice(float price) {
 
        this.price = price;
 
    }
 
}