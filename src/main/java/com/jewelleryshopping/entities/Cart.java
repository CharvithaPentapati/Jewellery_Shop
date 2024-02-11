package com.jewelleryshopping.entities;
 
import java.util.ArrayList;
import java.util.List;
 
import jakarta.persistence.CascadeType;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
 
@Entity
public class Cart {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private int cartId;

 
	    @PositiveOrZero(message = "Cart item quantity must be positive or zero")
	    private int cartItemQuantity;
 
	    @Positive(message = "Cart total price must be positive")
	    private float cartTotalPrice;
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;

	    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	    private List<Product> products = new ArrayList<>();

 
		public int getCartId() {
			return cartId;
		}
		public void setCartId(int cartId) {
			this.cartId = cartId;
		}
		public User getUser() {
			return user;
		}
		public void setUser(User user) {
			this.user = user;
		}
		public List<Product> getProducts() {
			return products;
		}
		public void setProducts(List<Product> products2) {
			this.products = products2;
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
		public Cart() {
			super();
		}
		public Cart(int cartId, int cartItemQuantity, float  cartTotalPrice) {
			super();
			this.cartId = cartId;
			this.cartItemQuantity = cartItemQuantity;
			this.cartTotalPrice = cartTotalPrice;
		}
 
 
}