package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckupPackpageRepository extends JpaRepository<CheckupPackpage, String >{
    @Query("SELECT cp FROM CheckupPackpage cp WHERE cp.packageId = :package_id")
    Optional<CheckupPackpage> findById(@Param("package_id") String package_id);

    @Modifying
    @Query(value = "INSERT INTO checkuppackpage (package_id, name, description, amount) VALUES (:package_id, :name, :description, :amount)", nativeQuery = true)
    void insertCheckupPackage(String package_id, String name, String description, Double amount);

}