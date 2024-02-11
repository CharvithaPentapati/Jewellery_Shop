package com.jewelleryshopping.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.jewelleryshopping.dtos.UserDTO;

import com.jewelleryshopping.entities.User;
import com.jewelleryshopping.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
    private final ModelMapper modelMapper;
 
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
 
    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }
 
    @Override
    public UserDTO getUserById(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));
        return modelMapper.map(user, UserDTO.class);
    }
 
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDTO.class);
    }
 
    @Override
    public UserDTO updateUser(int userId, UserDTO userDTO) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + userId));

        modelMapper.map(userDTO, existingUser);
        existingUser.setUserId(userId);
        existingUser = userRepository.save(existingUser);
        return modelMapper.map(existingUser, UserDTO.class);
    }
 
    @Override
    public void deleteUser(int userId) {
        userRepository.deleteById(userId);
    }
 
    @Override
    public boolean isUserNameExists(String userName) {
        return userRepository.existsByUserName(userName);
    }

	@Override
	public User findById(int userId) {
		return userRepository.findByUserId(userId);
	}

}
