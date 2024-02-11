package com.jewelleryshopping.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
 
 
@Entity
public class Billing {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billingId;
 
    @PositiveOrZero(message = "Total cost must be a positive or zero value")
    private double totalCost;

    @NotBlank(message = "Bill number is required")
    @Size(min = 1, max = 50, message = "Bill number must be between 1 and 50 characters")
    private String billNo;

    
    @ManyToOne
    @NotNull(message = "User is required")
    private User user;
 
    public Billing() {
    }
 
    public Billing(double totalCost, String billNo, User user) {
        this.totalCost = totalCost;
        this.billNo = billNo;
        this.user = user;
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
 

 
 
    public Long getBillingId() {
		return billingId;
	}

	public void setBillingId(Long billingId) {
		this.billingId = billingId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String productDetails() {
        return "Product details for billNo: " + billNo;
    }
 
    public String billDetails() {
        return "Bill details - Bill No: " + billNo + ", Total Cost: " + totalCost;
    }
}

 
