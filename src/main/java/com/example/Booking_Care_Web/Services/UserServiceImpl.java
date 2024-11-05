package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

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

//    @Override
//    public User saveUser(User user){
//        return userRepository.save(user);
//    }

    @Transactional
    @Override
    public User saveUser(User user) {
        // Gọi phương thức tùy chỉnh để lưu người dùng
        userRepository.insertUser(user.getUserId(), user.getName(), user.getPhoneNumber(),
                user.getEmail(), user.getGender(), user.getAddress(),
                user.getDescription(), user.getIdentificationCard());
        return user;  // Trả về người dùng đã lưu
    }
}
