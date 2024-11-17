package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Dtos.AppointmentDTO;
import com.example.Booking_Care_Web.Models.Entities.Appointment;
import com.example.Booking_Care_Web.Services.AppointmentService;
import com.example.Booking_Care_Web.Services.AppointmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootTest
@SpringJUnitWebConfig
public class AppointmentServiceTest {
    @Autowired
    AppointmentServiceImpl appointmentServiceImp;

    @Test
    public void testFindAllAppointments() {
        List<AppointmentDTO> appointments = appointmentServiceImp.findAll();
        appointments.forEach(appointment -> System.out.println(appointment));
    }
    @Test
    public void testSaveAppointment() {
        AppointmentDTO appointmentDTO = AppointmentDTO.builder()
                .patient_id("123") // Cần thay thế bằng giá trị thực
                .doctor_id("456")  // Cần thay thế bằng giá trị thực
                .available_datetime(LocalDateTime.now())
                .package_id("pkg1")
                .examination_date(new Date())
                .time_id("time1")
                .note("Test note")
                .status("Scheduled")
                .build();

        AppointmentDTO savedAppointment = appointmentServiceImp.save(appointmentDTO);

        // In ra kết quả
        System.out.println(savedAppointment);

    }
}
