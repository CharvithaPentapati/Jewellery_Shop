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

import com.jewelleryshopping.dtos.CartDTO;
import com.jewelleryshopping.services.CartService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/carts")
@Validated
public class CartController {
	private final CartService cartService;
	 
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
 
    @GetMapping
    public ResponseEntity<List<CartDTO>> getAllCarts() {
        List<CartDTO> carts = cartService.getAllCarts();
        return ResponseEntity.ok(carts);
    }
 
    @GetMapping("/{cartId}")
    public ResponseEntity<CartDTO> getCartById(@PathVariable int cartId) {
        CartDTO cart = cartService.getCartById(cartId);
        return ResponseEntity.ok(cart);
    }
 
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartDTO>> getCartsByUserId(@PathVariable int userId) {
        List<CartDTO> carts = cartService.getCartsByUserId(userId);
        return ResponseEntity.ok(carts);
    }
 
    @PostMapping
    public ResponseEntity<CartDTO> createCart(@Valid @RequestBody CartDTO cartDTO) {
        CartDTO createdCart = cartService.saveCart(cartDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCart);
    }
 
    @PutMapping("/{cartId}")
    public ResponseEntity<CartDTO> updateCart(@PathVariable int cartId, @Valid @RequestBody CartDTO cartDTO) {
        cartDTO.setCartId(cartId);
        CartDTO updatedCart = cartService.updateCart(cartDTO);
        return ResponseEntity.ok(updatedCart);
    }
 
    @DeleteMapping("/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable int cartId) {
        cartService.deleteCart(cartId);
        return ResponseEntity.noContent().build();
    }
//    @GetMapping("/total-price")
//    public ResponseEntity<Double> getTotalPriceForCart() {
//        double totalPrice = cartService.getTotalPriceForCart();
//        return ResponseEntity.ok(totalPrice);
//    }
//
//    // New endpoint to get quantity of item
//    @GetMapping("/quantity/{itemId}")
//    public ResponseEntity<Integer> getQuantityOfItem(@PathVariable Long itemId) {
//        int quantity = cartService.getQuantityOfItem(itemId);
//        return ResponseEntity.ok(quantity);
//    }
	
}
