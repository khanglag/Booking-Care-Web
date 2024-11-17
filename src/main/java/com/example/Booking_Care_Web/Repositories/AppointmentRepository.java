package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
