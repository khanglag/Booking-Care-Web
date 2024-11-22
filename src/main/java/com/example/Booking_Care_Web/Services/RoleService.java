package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.RoleDTO;
import com.example.Booking_Care_Web.Models.Entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    @Autowired
    Role findById(String role_id);
    List<RoleDTO> findAll();
    Role saveRole(Role role);
}