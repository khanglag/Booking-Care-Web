package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Dtos.RoleDTO;
import com.example.Booking_Care_Web.Models.Entities.Role;
import com.example.Booking_Care_Web.Services.RoleService;
import com.example.Booking_Care_Web.Services.RoleServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;


@SpringBootTest
@SpringJUnitWebConfig
public class RoleServiceTest {
    @Autowired
    RoleServiceImpl roleServiceImp;

    @Test
    public void testCreateRole(){

        Role roleDTO = new Role();
        roleDTO.setRoleId("hihe");
        roleDTO.setName("haha");

        System.out.println(roleDTO);
        Role savedRole = roleServiceImp.saveRole(roleDTO);
        System.out.println(savedRole);
    }

    @Test
    public void testFindAllRole(){
        List<RoleDTO> roles = roleServiceImp.findAll();
        roles.forEach(role -> System.out.println(role));
    }

    @Test
    public void testFindById(){
        String id ="doctor0";
        Role role = roleServiceImp.findById(id);
        System.out.println("==============");
        System.out.println(role.toString());
    }

    @Test
    public void testUpdateRole(){
        Role roleDTO = new Role();
        roleDTO.setRoleId("hihe");
        roleDTO.setName("hihihi");

        Role update = roleServiceImp.updateRole("hihe",roleDTO);
    }
}
