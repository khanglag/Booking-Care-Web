package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.CheckupPackageDTO;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CheckupPackageService {
    @Autowired
    CheckupPackage findById(String package_id);
    List<CheckupPackageDTO> findAll();

}