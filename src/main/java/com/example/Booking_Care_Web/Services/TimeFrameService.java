package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.TimeFrameDTO;
import com.example.Booking_Care_Web.Models.Entities.TimeFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TimeFrameService {

    @Autowired
    TimeFrame findById(String id);

    List<TimeFrameDTO> findAll();

    TimeFrame saveTimeFrame(TimeFrame tf);
}