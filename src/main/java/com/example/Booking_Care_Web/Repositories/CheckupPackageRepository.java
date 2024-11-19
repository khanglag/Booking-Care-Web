package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.CheckupPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckupPackageRepository extends JpaRepository<CheckupPackage, String>{
    @Query("SELECT cp FROM CheckupPackage cp WHERE c.package_id = :pakage_id")
    Optional<CheckupPackage> findById(@Param("id") String id);

    @Modifying
    @Query(value = "INSERT INTO checkuppackage (package_id,name,description,amount) VALUES (:package_id, :name, :description, :amount)", nativeQuery = true)
    void insertCheckupPackage(String package_id, String name, String description, double amount);

}