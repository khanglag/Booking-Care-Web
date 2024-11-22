package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.CheckupPackpageDTO;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CheckupPackpageService {
    @Autowired
    CheckupPackpage findById(String package_id);
    List<CheckupPackpageDTO> findAll();

}