package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Services.EmailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringBootTest
public class EmailServiceTest {
    @Autowired
    EmailService emailService;
    @Test
    public void testEmailService() {
        String toEmail ="khangminh.do2003@gmail.com";
        String patientName = "Khang Minh";
        String appointmentDate = "22/12/2020";
        String appointmentTime = "11:30";
        String doctorName = "Khang";
        String location = "hehe";
        String phoneNumber = "123456789";

        try {
            emailService.sendAppointmentConfirmationEmail(toEmail,patientName,appointmentDate,appointmentTime,doctorName,location,phoneNumber);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
