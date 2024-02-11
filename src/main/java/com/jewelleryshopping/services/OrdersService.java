package com.jewelleryshopping.services;
import com.jewelleryshopping.dtos.OrdersDTO;

import com.jewelleryshopping.entities.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrdersService {
    List<OrdersDTO> getAllOrders();
    Optional<OrdersDTO> getOrderById(int id);
    OrdersDTO createOrder(OrdersDTO orderDTO);
    OrdersDTO updateOrder(int id, OrdersDTO orderDTO);
    void deleteOrder(int id);
 
    List<OrdersDTO> getOrdersByUserId(int userId);
 
    List<OrdersDTO> getOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate);
 
    List<OrdersDTO> getOrdersWithCostGreaterThan(float minOrderCost);
	boolean isOrderDateExists(LocalDateTime orderDate);
	boolean isUserExists(User user);
}

