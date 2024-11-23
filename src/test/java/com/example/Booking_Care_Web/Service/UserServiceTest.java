package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Dtos.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

@SpringBootTest
@SpringJUnitWebConfig
public class UserServiceTest {



    @Autowired
    private UserServiceImpl userServiceImp;

    @Test
    public void testCreateUser() {

        User userDTO = new User();
        userDTO.setUserId(userServiceImp.createNewUserId("pt"));
        userDTO.setName("John Doe");
        userDTO.setPhoneNumber("1234567890");
        userDTO.setEmail("khangminh.do2@gmail.com");
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

        String email ="Admin@gmai.com";
        User user = userServiceImp.findByEmail(email);
        System.out.println(user.toString());
    }
    @Test
    public void testFindById(){
        String id ="doctor1";
        User user = userServiceImp.findById(id);
        System.out.println("==========================");
        System.out.println(user.toString());
    }

    @Test
    public void testUpdateUser() {
        User userDTO = new User();
        userDTO.setUserId("pt0281");
        userDTO.setName("John");
        userDTO.setPhoneNumber("1234567890");
        userDTO.setEmail("johndoe@example.com");
        userDTO.setGender("Male");
        userDTO.setAddress("123 Street");
        userDTO.setDescription("Test description");
        userDTO.setIdentificationCard("ID123456");

        User update = userServiceImp.updateUser("000281", userDTO);
    }

    @Test
    public void testFindMaxPatientId(){
        System.out.println(userServiceImp.createNewUserId("doctor"));
    }

    @Test
    public void testFindAllDoctor(){
        List<UserDTO> listUser = userServiceImp.findAllDoctors();
        listUser.forEach(list -> System.out.println(list));
    }

}