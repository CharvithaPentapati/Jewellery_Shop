package com.jewelleryshopping.JewelleryShopping;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import com.jewelleryshopping.dtos.UserDTO;
import com.jewelleryshopping.entities.User;
import com.jewelleryshopping.repositories.UserRepository;
import com.jewelleryshopping.services.UserServiceImpl;

public class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        modelMapper = new ModelMapper();
        userService = new UserServiceImpl(userRepository, modelMapper);
    }

    @Test
    void testGetAllUsers() {
        // Given
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        when(userRepository.findAll()).thenReturn(users);

        // When
        List<UserDTO> userDTOs = userService.getAllUsers();

        // Then
        assertEquals(users.size(), userDTOs.size());
    }

    @Test
    void testGetUserById() {
        // Given
        int id = 1;
        User user = new User();
        user.setUserId(id);
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        // When
        UserDTO userDTO = userService.getUserById(id);

        // Then
        assertNotNull(userDTO);
        assertEquals(id, userDTO.getUserId());
    }


    @Test
    void testDeleteUser() {
        // Given
        int id = 1;

        // When
        userService.deleteUser(id);

        // Then
        verify(userRepository, times(1)).deleteById(id);
    }
}
