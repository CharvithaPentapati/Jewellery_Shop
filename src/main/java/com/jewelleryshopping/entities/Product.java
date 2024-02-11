package com.jewelleryshopping.entities;
 
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
 
 
@Entity
public class Product {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @NotBlank(message = "Product name is required")
    @Column(name = "product_name", nullable = false)
    private String productName;
 
    private String category;
    @PositiveOrZero(message = "Quantity must be positive or zero")
    private int quantity;
 
    @Positive(message = "Price must be positive")
    private float price;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;
    // Constructors
    public Product() {
    }

    // Getters and setters
    public Long getproductId() {
        return productId;
    }
    public void setproductId(Long productId) {
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