package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.RoleDTO;
import com.example.Booking_Care_Web.Models.Entities.Role;
import com.example.Booking_Care_Web.Repositories.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Role findById(String role_id) {
        return roleRepository.findById(role_id)
                .orElseThrow(() -> new EntityNotFoundException("cp không tìm thấy với ID: " + role_id));
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll().stream()
                .map(role -> new RoleDTO(
                        role.getRoleId(),
                        role.getName()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Role saveRole(Role role){
        roleRepository.insertRole(role.getRoleId(),role.getName());
        return role;
    }
}