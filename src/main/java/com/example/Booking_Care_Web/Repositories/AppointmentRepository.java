package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("select d from Appointment d where d.doctor.userId = :id")
    List<Appointment> findByDoctorId(String id);

    @Query("select d from Appointment d where d.patient.userId = :id")
    List<Appointment> findByPatientId(String id);

    @Query("SELECT a from  Appointment a WHERE a.status = :status")
    List<Appointment> findByStatus(String status);

}
