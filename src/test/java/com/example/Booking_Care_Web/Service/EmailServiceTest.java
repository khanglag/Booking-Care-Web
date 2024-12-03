package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringBootTest
@SpringJUnitWebConfig
public class EmailServiceTest {
    @Autowired
    EmailService emailService;
}
