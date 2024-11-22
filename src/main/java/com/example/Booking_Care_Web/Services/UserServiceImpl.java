package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).get();
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserDTO(
                        user.getUserId(),
                        user.   getName(),
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
    @Override
    public User saveUser(User user) {
        // Gọi phương thức tùy chỉnh để lưu người dùng

        userRepository.insertUser(user.getUserId(), user.getName(), user.getPhoneNumber(),
                user.getEmail(), user.getGender(), user.getAddress(),
                user.getDescription(), user.getIdentificationCard());
        return user;  // Trả về người dùng đã lưu
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
}
