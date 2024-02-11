package com.jewelleryshopping.repositories;

import com.jewelleryshopping.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	boolean existsByUserName(String userName);
	 //public boolean isUserExists(UserDto userDto);

	User findByUserId(int userId);
	}
