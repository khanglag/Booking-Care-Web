package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElse(null);
    }

    @Override
    public User findById(String id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public List<UserDTO> findAll() {
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

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);  // Trả về người dùng đã lưu
    }

    @Override
    public String findMaxUserId(String str) {
        return userRepository.findMaxUserId(str);
    }

    public  User updateUser(String id, User updateUser) {
        User userExisting = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User không tồn tại ID"+id));

        if (updateUser.getName() != null) {
            userExisting.setName(updateUser.getName());
        }
        if (updateUser.getPhoneNumber() != null) {
            userExisting.setPhoneNumber(updateUser.getPhoneNumber());
        }
        if (updateUser.getEmail() != null) {
            userExisting.setEmail(updateUser.getEmail());
        }
        if (updateUser.getGender() != null) {
            userExisting.setGender(updateUser.getGender());
        }
        if (updateUser.getAddress() != null) {
            userExisting.setAddress(updateUser.getAddress());
        }

        // Lưu lại vào cơ sở dữ liệu
        return userRepository.save(userExisting);
    }

    // Tạo userId
    public String createNewUserId(String str){
        String maxPatientId = userRepository.findMaxUserId(str);
        String numberPart;
        int newId;
        if(str.equals("pt")){
            numberPart = maxPatientId.substring(2);
            newId = Integer.parseInt(numberPart) + 1;
            return "pt" + String.format("%05d",newId);
        } else if(str.equals("doctor")){
            numberPart = maxPatientId.substring(6);
            newId = Integer.parseInt(numberPart) + 1;
            return "doctor" + newId;
        } else if(str.equals("admin")){
            numberPart = maxPatientId.substring(5);
            newId = Integer.parseInt(numberPart) + 1;
            return "admin" + String.format("%02d",newId);
        } else {
            numberPart = maxPatientId.substring(2);
            newId = Integer.parseInt(numberPart) + 1;
            return "sp" + String.format("%05d",newId);
        }
    }
}
