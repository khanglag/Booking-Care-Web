package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.User.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getUserId(),
                        user.getName(),
                        user.getPhoneNumber(),
                        user.getEmail(),
                        user.getGender(),
                        user.getAddress(),
                        user.getDescription(),
                        user.getIdentificationCard()
                ))
                .collect(Collectors.toList());
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
