package com.jewelleryshopping.JewelleryShopping;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.jewelleryshopping.dtos.CartDTO;
import com.jewelleryshopping.dtos.ProductDTO;
import com.jewelleryshopping.entities.Cart;
import com.jewelleryshopping.entities.Product;
import com.jewelleryshopping.entities.User;
import com.jewelleryshopping.repositories.CartRepository;
import com.jewelleryshopping.services.CartServiceImpl;
import com.jewelleryshopping.services.ProductService;
import com.jewelleryshopping.services.UserService;

public class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private CartServiceImpl cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllCarts_Valid() {
        // Given
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart());
        when(cartRepository.findAll()).thenReturn(carts);
        when(modelMapper.map(any(Cart.class), eq(CartDTO.class))).thenReturn(new CartDTO());

        // When
        List<CartDTO> cartDTOs = cartService.getAllCarts();

        // Then
        assertEquals(carts.size(), cartDTOs.size());
    }

	/*
	 * @Test void testGetCartById_Valid() { // Given int cartId = 1; Cart cart = new
	 * Cart(); cart.setCartId(cartId);
	 * when(cartRepository.findById(cartId)).thenReturn(Optional.of(cart));
	 * when(modelMapper.map(any(Cart.class), eq(CartDTO.class))).thenReturn(new
	 * CartDTO());
	 * 
	 * // When CartDTO cartDTO = cartService.getCartById(cartId);
	 * 
	 * // Then assertNotNull(cartDTO); assertEquals(cartId, cartDTO.getCartId()); }
	 */

    @Test
    void testGetCartById_Invalid() {
        // Given
        int cartId = 2;
        when(cartRepository.findById(cartId)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> cartService.getCartById(cartId));
    }

    @Test
    void testGetCartsByUserId_Valid() {
        // Given
        int userId = 1;
        List<Cart> carts = new ArrayList<>();
        carts.add(new Cart());
        when(cartRepository.findByUserUserId(userId)).thenReturn(carts);
        when(modelMapper.map(any(Cart.class), eq(CartDTO.class))).thenReturn(new CartDTO());

        // When
        List<CartDTO> cartDTOs = cartService.getCartsByUserId(userId);

        // Then
        assertEquals(carts.size(), cartDTOs.size());
    }

    @Test
    void testUpdateCart_Invalid() {
        // Given
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartId(2);
        when(cartRepository.findById(2)).thenReturn(Optional.empty());

        // When/Then
        assertThrows(RuntimeException.class, () -> cartService.updateCart(cartDTO));
    }

    @Test
    void testDeleteCart_Valid() {
        // Given
        int cartId = 1;

        // When
        cartService.deleteCart(cartId);

        // Then
        verify(cartRepository, times(1)).deleteById(cartId);
    }
}
