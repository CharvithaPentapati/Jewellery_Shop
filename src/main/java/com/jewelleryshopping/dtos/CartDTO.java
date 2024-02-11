package com.jewelleryshopping.dtos;
 
import java.util.HashSet;
import java.util.Set;
 
import com.jewelleryshopping.entities.User;
 
public class CartDTO{
	private int cartId;
    private int cartItemQuantity;
    private float cartTotalPrice;
    private User user;
    private Set<ProductDTO> products = new HashSet<>();
    // Getters and setters
    public int getCartId() {
        return cartId;
    }
    public void setCartId(int cartId) {
        this.cartId = cartId;
    }
    public int getCartItemQuantity() {
        return cartItemQuantity;
    }
    public void setCartItemQuantity(int cartItemQuantity) {
        this.cartItemQuantity = cartItemQuantity;
    }
    public float getCartTotalPrice() {
        return cartTotalPrice;
    }
    public void setCartTotalPrice(float cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Set<ProductDTO> getProducts() {
        return products;
    }
    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}