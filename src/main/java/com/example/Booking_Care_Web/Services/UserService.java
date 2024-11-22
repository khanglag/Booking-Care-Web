package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
   @Autowired
   User findByEmail(String email);
   User findById(String id);
   List<UserDTO> findAll();
   String findMaxUserId(String str);

}
