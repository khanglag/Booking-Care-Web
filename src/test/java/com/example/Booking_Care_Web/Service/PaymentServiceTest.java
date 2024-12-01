package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Dtos.PaymentDTO;
import com.example.Booking_Care_Web.Models.Entities.Payment;
import com.example.Booking_Care_Web.Services.PaymentService;
import com.example.Booking_Care_Web.Services.PaymentServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

@SpringBootTest
@SpringJUnitWebConfig
public class PaymentServiceTest {
    @Autowired
    private PaymentServiceImp paymentService;

    @Test
    public void testFindAll() {
        List<PaymentDTO> payments = paymentService.findAll();
        payments.forEach(System.out::println);
    }
}
