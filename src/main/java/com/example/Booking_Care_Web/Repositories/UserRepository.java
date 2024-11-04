package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM User u WHERE u.userId = :userId")
    Optional<User> findById(@Param("userId") String userId);

    @Modifying
    @Query(value = "INSERT INTO user (user_id, name, phone_number, email, gender, address, description, identification_card) VALUES (:userId, :name, :phoneNumber, :email, :gender, :address, :description, :identificationCard)", nativeQuery = true)
    void insertUser(String userId, String name, String phoneNumber, String email, String gender, String address, String description, String identificationCard);

}
