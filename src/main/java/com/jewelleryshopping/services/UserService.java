package com.jewelleryshopping.services;

import com.jewelleryshopping.dtos.UserDTO;
import com.jewelleryshopping.entities.User;

import java.util.List;

public interface UserService {
	List<UserDTO> getAllUsers();
    UserDTO getUserById(int userId);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(int userId, UserDTO userDTO);
    void deleteUser(int userId);
    boolean isUserNameExists(String userName);
	User findById(int userId);
}
