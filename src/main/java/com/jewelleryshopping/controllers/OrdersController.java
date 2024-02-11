package com.jewelleryshopping.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jewelleryshopping.dtos.OrdersDTO;
import com.jewelleryshopping.services.OrdersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
@Validated
public class OrdersController {
 
    private final OrdersService ordersService;
 
    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrders() {
        List<OrdersDTO> orders = ordersService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrderById(@PathVariable int id) {
        Optional<OrdersDTO> order = ordersService.getOrderById(id);
        return order.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
 
    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrdersDTO orderDTO) {
        if (ordersService.isOrderDateExists(orderDTO.getOrderDate()) && ordersService.isUserExists(orderDTO.getUser())) {
            Map<String, String> error = new HashMap<>();
            error.put("Order Exists", "Order exists by the same user on the same date");
            return ResponseEntity.badRequest().body(error);
        }
        OrdersDTO createdOrder = ordersService.createOrder(orderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<OrdersDTO> updateOrder(@PathVariable int id, @Valid @RequestBody OrdersDTO orderDTO) {
        OrdersDTO updatedOrder = ordersService.updateOrder(id, orderDTO);
        return ResponseEntity.ok(updatedOrder);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable int id) {
        ordersService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
 
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrdersDTO>> getOrdersByUserId(@PathVariable int userId) {
        List<OrdersDTO> orders = ordersService.getOrdersByUserId(userId);
        return ResponseEntity.ok(orders);
    }
 
    @GetMapping("/dates")
    public ResponseEntity<List<OrdersDTO>> getOrdersBetweenDates(
            @RequestParam("start") @DateTimeFormat LocalDateTime startDate,
            @RequestParam("end") @DateTimeFormat LocalDateTime endDate) {
        List<OrdersDTO> orders = ordersService.getOrdersBetweenDates(startDate, endDate);
        return ResponseEntity.ok(orders);
    }
 
    @GetMapping("/cost")
    public ResponseEntity<List<OrdersDTO>> getOrdersWithCostGreaterThan(@RequestParam("minCost") float minOrderCost) {
        List<OrdersDTO> orders = ordersService.getOrdersWithCostGreaterThan(minOrderCost);
        return ResponseEntity.ok(orders);
    }
}