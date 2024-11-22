package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.AppointmentDTO;
import com.example.Booking_Care_Web.Models.Entities.Appointment;
import com.example.Booking_Care_Web.Repositories.AppointmentRepository;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> new AppointmentDTO(
                        appointment.getAppointmentId(),
                        appointment.getPatient().getUserId(),
                        appointment.getDoctor().getUserId(),
                        appointment.getAvailableDatetime(),
                        appointment.getPackageId(),
                        appointment.getExaminationDay(),
                        appointment.getTimeId(),
                        appointment.getNote(),
                        appointment.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public Appointment save(Appointment appointment) {
       return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        return appointment;
    }

    @Override
    public List<Appointment> findAppointmentByPatientId(String id) {
        return appointmentRepository.findByPatientId(id);
    }

    @Override
    public List<Appointment> findAppointmentByDoctorId(String id) {
        return appointmentRepository.findByDoctorId(id);
    }

    @Transactional
    public Appointment updateAppointment(Appointment appointment) {
        Appointment appointment1 = appointmentRepository.findById(appointment.getAppointmentId()).orElse(null);
        if (appointment1 == null) {
            throw new RuntimeException("Appointment not found with ID: " + appointment.getAppointmentId());
        }
        appointment1.setPatient(appointment.getPatient());
        appointment1.setDoctor(appointment.getDoctor());
        appointment1.setAvailableDatetime(appointment.getAvailableDatetime());
        appointment1.setPackageField(appointment.getPackageField());
        appointment1.setExaminationDay(appointment.getExaminationDay());
        appointment1.setTime(appointment.getTime());
        appointment1.setNote(appointment.getNote());
        appointment1.setStatus(appointment.getStatus());
        return appointmentRepository.save(appointment1);
    }

}
