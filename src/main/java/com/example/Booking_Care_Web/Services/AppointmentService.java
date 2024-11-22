package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.AppointmentDTO;
import com.example.Booking_Care_Web.Models.Entities.Appointment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AppointmentService {
    List<AppointmentDTO> findAll();
    Appointment findAppointmentById(int id);
    List<Appointment> findAppointmentByPatientId(String id);
    List<Appointment> findAppointmentByDoctorId(String id);
}
