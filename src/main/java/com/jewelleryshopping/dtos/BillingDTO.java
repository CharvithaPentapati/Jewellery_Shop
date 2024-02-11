package com.jewelleryshopping.dtos;

import com.jewelleryshopping.entities.User;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class BillingDTO{
	private Long billingId;
	 
    @PositiveOrZero(message = "Total cost must be a positive or zero value")
    private double totalCost;
 
    @NotBlank(message = "Bill number is required")
    @Size(min = 1, max = 50, message = "Bill number must be between 1 and 50 characters")
    private String billNo;
 
    @NotNull(message = "User is required")
    private User user;
 
    // Constructors, getters, setters
 
    public BillingDTO() {
    }
 
    public BillingDTO(double totalCost, String billNo, User user) {
        this.totalCost = totalCost;
        this.billNo = billNo;
        this.user = user;
    }
 
    public Long getBillingId() {
        return billingId;
    }
 
    public void setBillingId(Long billingId) {
        this.billingId = billingId;
    }
 
    public double getTotalCost() {
        return totalCost;
    }
 
    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
 
    public String getBillNo() {
        return billNo;
    }
 
    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
 
    public User getUser() {
        return user;
    }
 
    public void setUser(User user) {
        this.user = user;
    }
	
}