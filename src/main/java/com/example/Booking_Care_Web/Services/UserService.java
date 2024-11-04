package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.User.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public interface UserService {
   @Autowired
   User findByEmail(String email);
   User findById(String id);
   List<UserDTO> findAll();
   User saveUser(User user);


}
