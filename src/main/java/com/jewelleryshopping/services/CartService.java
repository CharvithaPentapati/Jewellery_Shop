package com.jewelleryshopping.services;

import com.jewelleryshopping.dtos.CartDTO;
import com.jewelleryshopping.entities.Cart;

import java.util.List;

public interface CartService {
	List<CartDTO> getAllCarts();
	 
    CartDTO getCartById(int cartId);
 
    List<CartDTO> getCartsByUserId(int userId); 
 
    void deleteCart(int cartId);

	Cart findById(int cartId);

	CartDTO saveCart(CartDTO cartDTO);

	CartDTO updateCart(CartDTO cartDTO);

	//double getTotalPriceForCart();

	//int getQuantityOfItem(Long itemId);
	
}
