package com.jewelleryshopping.services;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
 
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
 
import com.jewelleryshopping.dtos.OrdersDTO;
import com.jewelleryshopping.entities.Orders;
import com.jewelleryshopping.entities.User;
import com.jewelleryshopping.repositories.*;
@Service
 
public class OrdersServiceImpl implements OrdersService {
    private final OrdersRepository ordersRepository;
 
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CartService cartService;
    @Autowired
 
    public OrdersServiceImpl(OrdersRepository ordersRepository, ModelMapper modelMapper,UserService userService,CartService cartService) {
 
        this.ordersRepository = ordersRepository;
 
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.cartService = cartService;
 
    }
    @Override
 
    public List<OrdersDTO> getAllOrders() {
 
        List<Orders> orders = ordersRepository.findAll();
 
        return orders.stream()
 
                .map(order -> modelMapper.map(order, OrdersDTO.class))
 
                .collect(Collectors.toList());
 
    }
    @Override
 
    public Optional<OrdersDTO> getOrderById(int id) {
 
        Optional<Orders> optionalOrder = ordersRepository.findById(id);
 
        return optionalOrder.map(order -> modelMapper.map(order, OrdersDTO.class));
 
    }
    @Override
 
    public OrdersDTO createOrder(OrdersDTO orderDTO) {
    	Orders order = new Orders();
    	order.setOrderDate(orderDTO.getOrderDate());
    	order.setOrderCost(orderDTO.getOrderCost());
    	order.setOrderStatus(orderDTO.getOrderStatus());
    	order.setUser(userService.findById(orderDTO.getUserId()));
    	order.setCart(cartService.findById(orderDTO.getCartId()));

 
        order = ordersRepository.save(order);
 
        return modelMapper.map(order, OrdersDTO.class);
 
    }
    @Override
    public OrdersDTO updateOrder(int id, OrdersDTO orderDTO) {
        Orders existingOrder = ordersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found with id: " + id));
        
        existingOrder.setOrderDate(orderDTO.getOrderDate());
        existingOrder.setOrderCost(orderDTO.getOrderCost());
        existingOrder.setOrderStatus(orderDTO.getOrderStatus());
        existingOrder.setUser(userService.findById(orderDTO.getUserId()));
        existingOrder.setCart(cartService.findById(orderDTO.getCartId()));

        existingOrder.setOrderId(id);
        Orders updatedOrder = ordersRepository.save(existingOrder);

        return modelMapper.map(updatedOrder, OrdersDTO.class);
    }

 

    @Override
 
    public void deleteOrder(int id) {
 
        ordersRepository.deleteById(id);
 
    }
    @Override
 
    public List<OrdersDTO> getOrdersByUserId(int userId) {
 
        List<Orders> orders = ordersRepository.findByUserUserId(userId);
 
        return orders.stream()
 
                .map(order -> modelMapper.map(order, OrdersDTO.class))
 
                .collect(Collectors.toList());
 
    }
    @Override
 
    public List<OrdersDTO> getOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
 
        List<Orders> orders = ordersRepository.findByOrderDateBetween(startDate, endDate);
 
        return orders.stream()
 
                .map(order -> modelMapper.map(order, OrdersDTO.class))
 
                .collect(Collectors.toList());
 
    }
    @Override
 
    public List<OrdersDTO> getOrdersWithCostGreaterThan(float minOrderCost) {
 
        List<Orders> orders = ordersRepository.findByOrderCostGreaterThanEqual(minOrderCost);
 
        return orders.stream()
 
                .map(order -> modelMapper.map(order, OrdersDTO.class))
 
                .collect(Collectors.toList());
 
    }
    @Override
 
    public boolean isOrderDateExists(LocalDateTime orderDate) {
 
        return ordersRepository.existsByOrderDate(orderDate);
 
    }
    @Override
 
    public boolean isUserExists(User user) {
 
        return ordersRepository.existsByUser(user);
 
    }
 
}