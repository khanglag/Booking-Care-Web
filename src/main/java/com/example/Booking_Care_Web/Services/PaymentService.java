package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.PaymentDTO;
import com.example.Booking_Care_Web.Models.Entities.Payment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentService {
    List<PaymentDTO> findAll();
    List<Payment> findByAppointmentId(int id);
}
