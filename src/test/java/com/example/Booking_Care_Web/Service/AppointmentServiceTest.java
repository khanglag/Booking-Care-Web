package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Dtos.AppointmentDTO;
import com.example.Booking_Care_Web.Models.Entities.Appointment;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
import com.example.Booking_Care_Web.Models.Entities.TimeFrame;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Services.AppointmentService;
import com.example.Booking_Care_Web.Services.AppointmentServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDate;
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
        User patient = new User();  // Giả định User đã được tạo với ID "pt00001"
        patient.setUserId("pt00002");

        User doctor = new User();  // Giả định User đã được tạo với ID "doctor1"
        doctor.setUserId("doctor2");

        CheckupPackpage checkupPackpage = new CheckupPackpage();
        checkupPackpage.setPackageId("tq20000");

        TimeFrame timeFrame = new TimeFrame();
        timeFrame.setTimeId("0000001");

        Appointment appointment = Appointment.builder()
                .patient(patient)
                .doctor(doctor)  // Cần thay thế bằng giá trị thực
                .availableDatetime(LocalDateTime.now())
                .packageField(checkupPackpage)
                .examinationDay(LocalDate.now())
                .time(timeFrame)
                .note("Test note")
                .status("Scheduled")
                .build();

        Appointment savedAppointment = appointmentServiceImp.save(appointment);

        // In ra kết quả
        System.out.println(savedAppointment);

    }
    @Test
    public void testFindByAppointmentId(){
        int id = 1;
        Appointment appointment = appointmentServiceImp.findAppointmentById(id);
        System.out.println(appointment);
    }

    @Test
    public void testFindAppointmentByPatientId(){
        String patientId = "pt00001";
        List<Appointment> appointments = appointmentServiceImp.findAppointmentByPatientId(patientId);
        appointments.forEach(appointment -> System.out.println(appointment));
    }

    @Test
    public void testFindAppointmentByDoctorId(){
        String doctorId = "doctor1";
        List<Appointment> appointments = appointmentServiceImp.findAppointmentByDoctorId(doctorId);
        appointments.forEach(appointment -> System.out.println(appointment));
    }

    @Test
    public void testUpdateAppointment(){
        User patient = new User();  // Giả định User đã được tạo với ID "pt00001"
        patient.setUserId("pt00001");

        User doctor = new User();  // Giả định User đã được tạo với ID "doctor1"
        doctor.setUserId("doctor1");

        CheckupPackpage checkupPackpage = new CheckupPackpage();
        checkupPackpage.setPackageId("tq20000");

        TimeFrame timeFrame = new TimeFrame();
        timeFrame.setTimeId("0000001");

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(1);
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAvailableDatetime(LocalDateTime.now());
        appointment.setPackageField(checkupPackpage);
        appointment.setExaminationDay(LocalDate.now());
        appointment.setTime(timeFrame);
        appointment.setNote("Test");
        appointment.setStatus("DA DUYET");
        System.out.println(appointment);
        System.out.println(appointmentServiceImp.updateAppointment(appointment));
    }

    @Test
    public void testFindAppointmentByStatus(){
        List<Appointment> appointment = appointmentServiceImp.findApppontmentByStatus("Scheduled");
        appointment.forEach(app -> System.out.println(app));
    }

}
