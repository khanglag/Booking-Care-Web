package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.AppointmentDTO;
import com.example.Booking_Care_Web.Models.Entities.Appointment;
import com.example.Booking_Care_Web.Repositories.AppointmentRepository;
import com.example.Booking_Care_Web.Repositories.UserRepository;
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

        // Ánh xạ từ DTO sang Entity
        appointment.setAppointmentId(appointmentDTO.getAppointmentId());
        appointment.setAvailableDatetime(appointmentDTO.getAvailable_datetime());
        appointment.setExaminationDay(appointmentDTO.getExamination_date());
        appointment.setNote(appointmentDTO.getNote());
        appointment.setStatus(appointmentDTO.getStatus());

        // Lấy dữ liệu `User` cho `patient` và `doctor`
        appointment.setPatient(userRepository.findById(appointmentDTO.getPatient_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid patient ID: " + appointmentDTO.getPatient_id())));

        appointment.setDoctor(userRepository.findById(appointmentDTO.getDoctor_id())
                .orElseThrow(() -> new IllegalArgumentException("Invalid doctor ID: " + appointmentDTO.getDoctor_id())));

//        // Lấy dữ liệu `CheckupPackpage` nếu có
//        if (appointmentDTO.getPackage_id() != null) {
//            appointment.setPackageField(checkupPackageRepository.findById(appointmentDTO.getPackage_id())
//                    .orElseThrow(() -> new IllegalArgumentException("Invalid package ID: " + appointmentDTO.getPackage_id())));
//        }
//
//        // Lấy dữ liệu `TimeFrame`
//        appointment.setTime(timeFrameRepository.findById(appointmentDTO.getTime_id())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid time ID: " + appointmentDTO.getTime_id())));

        // Lưu dữ liệu vào database
        Appointment savedAppointment = appointmentRepository.save(appointment);

        // Trả về DTO sau khi lưu
        return AppointmentDTO.builder()
                .appointmentId(savedAppointment.getAppointmentId())
                .patient_id(savedAppointment.getPatient().getUserId())
                .doctor_id(savedAppointment.getDoctor().getUserId())
                .available_datetime(savedAppointment.getAvailableDatetime())
                .package_id(savedAppointment.getPackageField() != null ? savedAppointment.getPackageField().getPackageId() : null)
                .examination_date(savedAppointment.getExaminationDay())
                .time_id(savedAppointment.getTime().getTimeId())
                .note(savedAppointment.getNote())
                .status(savedAppointment.getStatus())
                .build();
    }


}
