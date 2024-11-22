package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query("SELECT r FROM Role r WHERE r.roleId = :role_id")
    Optional<Role> findById(@Param("role_id") String role_id);

    @Modifying
    @Query(value = "INSERT INTO role (role_id, name) VALUES (:role_id, :name)",nativeQuery = true)
    void insertRole(String role_id, String name);
}