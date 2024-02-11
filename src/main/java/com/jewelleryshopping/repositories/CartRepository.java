package com.jewelleryshopping.repositories;

import com.jewelleryshopping.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findByUserUserId(int userId);
    @Query("SELECT c FROM Cart c JOIN FETCH c.user")
    List<Cart> findAllWithUsers();
	Cart findByCartId(int cartId);
}
