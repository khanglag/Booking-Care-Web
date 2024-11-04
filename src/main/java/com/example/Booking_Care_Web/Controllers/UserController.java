package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.User.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();

        return users.stream().map(user -> new UserDTO(user.getId(),user.getName(),user.getPhone(),user.getEmail(),user.getGender(),user.getAddress(),user.getDescription(),user.getIdentificationCard()))
                .collect(Collectors.toList());
    }
    @GetMapping("/user/findByEmail")
    public ResponseEntity<User> findByEmail(@RequestParam String email) {
        User user = userService.findByEmail(email);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
