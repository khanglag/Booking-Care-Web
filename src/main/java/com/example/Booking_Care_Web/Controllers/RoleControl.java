package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.RoleDTO;
import com.example.Booking_Care_Web.Models.Entities.Role;
import com.example.Booking_Care_Web.Services.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class RoleControl {

    @Autowired
    private RoleServiceImpl roleServiceImpl;

    @GetMapping("/roles")
    public List<RoleDTO> findAll() { return roleServiceImpl.findAll();}

    @GetMapping("/id")
    public ResponseEntity<RoleDTO> getRoleById(@RequestParam String role_id){
        try {
            Role role = roleServiceImpl.findById(role_id);
            RoleDTO roleDTO = new RoleDTO(
                    role.getRoleId(),
                    role.getName()
            );
            return ResponseEntity.ok(roleDTO);
        } catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role){
        Role savedRole = roleServiceImpl.savedRole(role);
        return ResponseEntity.status(HttpStatus.CREATE).body(savedRole);
    }
}