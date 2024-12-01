package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Models.Entities.Role;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Services.AccountServiceImp;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;


    @Autowired
    private AccountServiceImp accountServiceImpl;

    @GetMapping("/users")
    public List<UserDTO> findAll() {
        return userServiceImpl.findAll();
    }
    @GetMapping("/email")
    public ResponseEntity<UserDTO> getUserByEmail(@RequestParam String email) {
        try {
            User user = userServiceImpl.findByEmail(email);
            UserDTO userDTO = new UserDTO(
                    user.getUserId(),
                    user.getName(),
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getGender(),
                    user.getAddress(),
                    user.getDescription(),
                    user.getIdentificationCard()
            );
            return ResponseEntity.ok(userDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/id")
    public ResponseEntity<UserDTO> getUserById(@RequestParam String id) {
        try{
            User user = userServiceImpl.findById(id);
            UserDTO userDTO = new UserDTO(
                    user.getUserId(),
                    user.getName(),
                    user.getPhoneNumber(),
                    user.getEmail(),
                    user.getGender(),
                    user.getAddress(),
                    user.getDescription(),
                    user.getIdentificationCard()
            );return ResponseEntity.ok(userDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userServiceImpl.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }
    @PostMapping("/edit/{id}")
    public ResponseEntity<User> editUser(@PathVariable String id, @RequestBody User user) {
        User savedUser = userServiceImpl.updateUser(id,user);
        return ResponseEntity.ok(savedUser);
    }
    @PostMapping(value = "register",consumes = "application/json")
    public Account register(@RequestBody User user) {
        Role role = new Role();
        role.setRoleId("patient");

        String userID =   userServiceImpl.createNewUserId("pt");
        user.setUserId(userID);

        Account account = new Account();
        account.setAccountId(userID);
        account.setUsername(user.getAccount().getUsername());
        account.setPassword(user.getAccount().getPassword());
        account.setRole(role);
        user.setAccount(account);
        userServiceImpl.saveUser(user);
        return   accountServiceImpl.saveAccount(account);
    }
}
