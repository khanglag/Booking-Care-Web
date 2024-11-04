package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import com.example.Booking_Care_Web.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;


public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUser() {

        String id = "U123456";
        String name = "John Doe";
        String phone = "0123456789";
        String email = "john.doe@example.com";
        String gt = "Male";
        String address = "123 Main St";
        String description= "New user";
        String identificationCard = "123456789012";
        User user = new User();
        user.setUserId(id);
        user.setName(name);
        user.setPhoneNumber(phone);
        user.setEmail(email);
        user.setGender(gt);
        user.setAddress(address);
        user.setDescription(description);
        user.setIdentificationCard(identificationCard);

        userService.createUser(user);
        verify(userRepository).save(user);
    }


}