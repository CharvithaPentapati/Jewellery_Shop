package com.jewelleryshopping.JewelleryShopping;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.jewelleryshopping.dtos.OrdersDTO;
import com.jewelleryshopping.entities.Cart;
import com.jewelleryshopping.entities.Orders;
import com.jewelleryshopping.entities.User;
import com.jewelleryshopping.repositories.OrdersRepository;
import com.jewelleryshopping.services.CartService;
import com.jewelleryshopping.services.OrdersServiceImpl;
import com.jewelleryshopping.services.UserService;

public class OrdersServiceImplTest {

    @Mock
    private OrdersRepository ordersRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserService userService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private OrdersServiceImpl ordersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetAllOrders_Valid() {
        // Given
        List<Orders> orders = new ArrayList<>();
        orders.add(new Orders());
        when(ordersRepository.findAll()).thenReturn(orders);
        when(modelMapper.map(any(Orders.class), eq(OrdersDTO.class))).thenReturn(new OrdersDTO());

        // When
        List<OrdersDTO> ordersDTOs = ordersService.getAllOrders();

        // Then
        assertEquals(orders.size(), ordersDTOs.size());
    }

    @Test
    void testGetOrderById_Invalid() {
        // Given
        int orderId = 2;
        when(ordersRepository.findById(orderId)).thenReturn(Optional.empty());

        // When/Then
        assertFalse(ordersService.getOrderById(orderId).isPresent());
    }

    @Test
    void testCreateOrder_Valid() {
        // Given
        OrdersDTO orderDTO = new OrdersDTO();
        orderDTO.setOrderDate(LocalDateTime.now());
        orderDTO.setOrderCost(100.0f);
        orderDTO.setOrderStatus("Pending");
        User user = new User();
        user.setUserId(1);
        orderDTO.setUserId(1);
        when(userService.findById(1)).thenReturn(user);
        when(cartService.findById(anyInt())).thenReturn(new Cart());
        when(modelMapper.map(any(Orders.class), eq(OrdersDTO.class))).thenReturn(orderDTO);
        when(ordersRepository.save(any(Orders.class))).thenReturn(new Orders());

        // When
        OrdersDTO createdOrderDTO = ordersService.createOrder(orderDTO);

        // Then
        assertNotNull(createdOrderDTO);
        assertEquals(orderDTO.getOrderCost(), createdOrderDTO.getOrderCost());
        assertEquals(orderDTO.getOrderStatus(), createdOrderDTO.getOrderStatus());
    }

    @Test
    void testUpdateOrder_Valid() {
        // Given
        int orderId = 1;
        OrdersDTO orderDTO = new OrdersDTO();
        orderDTO.setOrderId(orderId);
        orderDTO.setOrderDate(LocalDateTime.now());
        orderDTO.setOrderCost(150.0f);
        orderDTO.setOrderStatus("Shipped");
        orderDTO.setUserId(1);
        orderDTO.setCartId(1);
        Orders existingOrder = new Orders();
        existingOrder.setOrderId(orderId);
        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(userService.findById(1)).thenReturn(new User());
        when(cartService.findById(1)).thenReturn(new Cart());
        when(ordersRepository.save(any(Orders.class))).thenReturn(new Orders());
        when(modelMapper.map(any(Orders.class), eq(OrdersDTO.class))).thenReturn(orderDTO);

        // When
        OrdersDTO updatedOrderDTO = ordersService.updateOrder(orderId, orderDTO);

        // Then
        assertNotNull(updatedOrderDTO);
        assertEquals(orderId, updatedOrderDTO.getOrderId());
        assertEquals(orderDTO.getOrderCost(), updatedOrderDTO.getOrderCost());
        assertEquals(orderDTO.getOrderStatus(), updatedOrderDTO.getOrderStatus());
    }

    @Test
    void testDeleteOrder_Valid() {
        // Given
        int orderId = 1;

        // When
        ordersService.deleteOrder(orderId);

        // Then
        verify(ordersRepository, times(1)).deleteById(orderId);
    }
    @Test
    void testGetOrdersByUserId_Valid() {
        // Given
        int userId = 1;
        List<Orders> orders = new ArrayList<>();
        orders.add(new Orders());
        when(ordersRepository.findByUserUserId(userId)).thenReturn(orders);
        when(modelMapper.map(any(Orders.class), eq(OrdersDTO.class))).thenReturn(new OrdersDTO());

        // When
        List<OrdersDTO> ordersDTOs = ordersService.getOrdersByUserId(userId);

        // Then
        assertEquals(orders.size(), ordersDTOs.size());
    }

    // Test for getOrdersBetweenDates method
    @Test
    void testGetOrdersBetweenDates_Valid() {
        // Given
        LocalDateTime startDate = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2022, 1, 31, 23, 59);
        List<Orders> orders = new ArrayList<>();
        orders.add(new Orders());
        when(ordersRepository.findByOrderDateBetween(startDate, endDate)).thenReturn(orders);
        when(modelMapper.map(any(Orders.class), eq(OrdersDTO.class))).thenReturn(new OrdersDTO());

        // When
        List<OrdersDTO> ordersDTOs = ordersService.getOrdersBetweenDates(startDate, endDate);

        // Then
        assertEquals(orders.size(), ordersDTOs.size());
    }

    // Test for getOrdersWithCostGreaterThan method
    @Test
    void testGetOrdersWithCostGreaterThan_Valid() {
        // Given
        float minOrderCost = 100.0f;
        List<Orders> orders = new ArrayList<>();
        orders.add(new Orders());
        when(ordersRepository.findByOrderCostGreaterThanEqual(minOrderCost)).thenReturn(orders);
        when(modelMapper.map(any(Orders.class), eq(OrdersDTO.class))).thenReturn(new OrdersDTO());

        // When
        List<OrdersDTO> ordersDTOs = ordersService.getOrdersWithCostGreaterThan(minOrderCost);

        // Then
        assertEquals(orders.size(), ordersDTOs.size());
    }

    // Test for isOrderDateExists method
    @Test
    void testIsOrderDateExists_Exists() {
        // Given
        LocalDateTime orderDate = LocalDateTime.now();
        when(ordersRepository.existsByOrderDate(orderDate)).thenReturn(true);

        // When
        boolean exists = ordersService.isOrderDateExists(orderDate);

        // Then
        assertTrue(exists);
    }

    // Test for isOrderDateExists method when the order date does not exist
    @Test
    void testIsOrderDateExists_NotExists() {
        // Given
        LocalDateTime orderDate = LocalDateTime.now();
        when(ordersRepository.existsByOrderDate(orderDate)).thenReturn(false);

        // When
        boolean exists = ordersService.isOrderDateExists(orderDate);

        // Then
        assertFalse(exists);
    }
    
    @Test
    void testIsUserExists_Exists() {
        // Given
        User user = new User();
        when(ordersRepository.existsByUser(user)).thenReturn(true);

        // When
        boolean exists = ordersService.isUserExists(user);

        // Then
        assertTrue(exists);
    }

    @Test
    void testIsUserExists_NotExists() {
        // Given
        User user = new User();
        when(ordersRepository.existsByUser(user)).thenReturn(false);

        // When
        boolean exists = ordersService.isUserExists(user);

        // Then
        assertFalse(exists);
    }
    
}
