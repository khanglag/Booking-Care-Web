package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Dtos.User.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import com.example.Booking_Care_Web.Services.UserService;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {

        User userDTO = new User();
        userDTO.setUserId("000281");
        userDTO.setName("John Doe");
        userDTO.setPhoneNumber("1234567890");
        userDTO.setEmail("johndoe@example.com");
        userDTO.setGender("Male");
        userDTO.setAddress("123 Street");
        userDTO.setDescription("Test description");
        userDTO.setIdentificationCard("ID123456");

        System.out.println(userDTO);
        User savedUser = userServiceImp.saveUser(userDTO);
        System.out.println(savedUser);


    }

    @Test
    public void testFindByEmail() {

//        String email ="Admin@gmai.com";
//        Optional<UserDTO> userDTOOptional = userService.findByEmail(email);
//        System.out.println(userDTOOptional.toString());
    }


}