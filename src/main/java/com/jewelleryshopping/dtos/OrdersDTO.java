package com.jewelleryshopping.dtos;
 
import java.time.LocalDateTime;
 
import com.fasterxml.jackson.annotation.JsonFormat;
import com.jewelleryshopping.entities.Cart;
import com.jewelleryshopping.entities.User;
 
//Imports related to validation constraints from the Jakarta Bean Validation API.
//These annotations are used for validating the fields of the DTO.
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
public class OrdersDTO {
    private int orderId;
    @NotNull(message = "Order date is required")
    @FutureOrPresent(message = "Order Date can't be in the past")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;
    @NotBlank(message = "Order status is required")
    private String orderStatus;
    @Positive(message = "Cost cannot be negative")
    private float orderCost;
    private User user;
    private Cart cart;
    private int userId;
    private int cartId;

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public LocalDateTime getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public float getOrderCost() {
        return orderCost;
    }
    public void setOrderCost(float orderCost) {
        this.orderCost = orderCost;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
 
	public int getUserId() {
		return userId;
	}
 
	public void setUserId(int userId) {
		this.userId = userId;
	}
 
	public int getCartId() {
		return cartId;
	}
 
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
}