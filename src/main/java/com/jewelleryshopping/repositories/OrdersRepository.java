package com.jewelleryshopping.repositories;

import com.jewelleryshopping.entities.Orders;
import com.jewelleryshopping.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	List<Orders> findByUserUserId(int userId);
	 
    List<Orders> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
 
    @Query("SELECT o FROM Orders o WHERE o.orderCost >= ?1")
    List<Orders> findByOrderCostGreaterThanEqual(float minOrderCost);
 
	boolean existsByOrderDate(LocalDateTime orderDate);
 
	boolean existsByUser(User user);

	void deleteByOrderId(int orderId);
}
