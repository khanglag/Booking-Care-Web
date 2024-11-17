package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.AppointmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    List<AppointmentDTO> findAll();
    AppointmentDTO save(AppointmentDTO appointmentDTO);
}
