package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.AppointmentDTO;
import com.example.Booking_Care_Web.Models.Entities.Appointment;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Repositories.AppointmentRepository;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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
                        appointment.getPackageField().getPackageId(),
                        appointment.getExaminationDay(),
                        appointment.getTime().getTimeId(),
                        appointment.getNote(),
                        appointment.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDTO save(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
        appointment.setPatientId(appointmentDTO.getPatient_id());
        appointment.setDoctorId(appointmentDTO.getDoctor_id());
        appointment.setAvailableDatetime(LocalDate.now());
        appointment.setPackageId(appointmentDTO.getPackage_id());
    }


}
