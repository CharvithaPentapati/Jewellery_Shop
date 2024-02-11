package com.jewelleryshopping.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jewelleryshopping.dtos.CartDTO;
import com.jewelleryshopping.dtos.ProductDTO;
import com.jewelleryshopping.entities.Cart;
import com.jewelleryshopping.entities.Product;
import com.jewelleryshopping.repositories.CartRepository;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final ProductService productService;
 
    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ModelMapper modelMapper, UserService userService,ProductService productService) {
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.productService = productService;
    }
 
    @Override
    public List<CartDTO> getAllCarts() {
        List<Cart> carts = cartRepository.findAll();
        return carts.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());
    }
 
    @Override
    public CartDTO getCartById(int cartId) {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));
        return modelMapper.map(cart, CartDTO.class);
    }
 
    @Override
    public List<CartDTO> getCartsByUserId(int userId) {
        List<Cart> carts = cartRepository.findByUserUserId(userId);
        return carts.stream()
                .map(cart -> modelMapper.map(cart, CartDTO.class))
                .collect(Collectors.toList());
    }
 
    @Override
    public CartDTO saveCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setUser(userService.findById(cartDTO.getUser().getUserId())); 
        cart.setCartItemQuantity(cartDTO.getCartItemQuantity());
        cart.setCartTotalPrice(cartDTO.getCartTotalPrice());
        List<Product> products = mapProductDTOsToProducts(cartDTO.getProducts());
        cart.setProducts(products);
        return modelMapper.map(cartRepository.save(cart), CartDTO.class);
    }

    @Override
    public CartDTO updateCart(CartDTO cartDTO) {
        Optional<Cart> optionalCart = cartRepository.findById(cartDTO.getCartId());
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setUser(userService.findById(cartDTO.getUser().getUserId())); 
            cart.setCartItemQuantity(cartDTO.getCartItemQuantity());
            cart.setCartTotalPrice(cartDTO.getCartTotalPrice());
            List<Product> products = mapProductDTOsToProducts(cartDTO.getProducts());
            cart.setProducts(products);
            return modelMapper.map(cartRepository.save(cart), CartDTO.class);
        } else {
            throw new RuntimeException("Cart not found with id: " + cartDTO.getCartId());
        }
    }

    private List<Product> mapProductDTOsToProducts(Set<ProductDTO> set) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : set) {
        	Product product = modelMapper.map(productDTO, Product.class);
            products.add(product);
        }
        return products;
    }


    @Override
    public void deleteCart(int cartId) {
        cartRepository.deleteById(cartId);
    }


	@Override
	public Cart findById(int cartId) {
		return cartRepository.findByCartId(cartId);
	}
	
	
}
