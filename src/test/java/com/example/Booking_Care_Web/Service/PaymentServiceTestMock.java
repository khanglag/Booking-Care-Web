package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Dtos.PaymentDTO;
import com.example.Booking_Care_Web.Models.Entities.Payment;
import com.example.Booking_Care_Web.Models.enumtype.PaymentMethod;
import com.example.Booking_Care_Web.Models.enumtype.StatusPayment;
import com.example.Booking_Care_Web.Repositories.PaymentRepository;
import com.example.Booking_Care_Web.Services.PaymentServiceImp;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentServiceTestMock {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentServiceImp paymentService;

    public PaymentServiceTestMock() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        // Mock dữ liệu
        Payment mockPayment1 = new Payment(1L, null, PaymentMethod.CREDIT, new BigDecimal("500.00"), LocalDateTime.now(), StatusPayment.SUCCESS);
        Payment mockPayment2 = new Payment(2L, null, PaymentMethod.CASH, new BigDecimal("300.00"), LocalDateTime.now(), StatusPayment.PENDING);

        when(paymentRepository.findAll()).thenReturn(Arrays.asList(mockPayment1, mockPayment2));

        // Thực hiện phương thức
        List<PaymentDTO> result = paymentService.findAll();

        // Kiểm tra kết quả
        assertEquals(2, result.size());
        assertEquals("CREDIT", result.get(0).getPaymentMethod());
        assertEquals("CASH", result.get(1).getPaymentMethod());
        assertEquals(new BigDecimal("500.00"), result.get(0).getAmount());
        assertEquals("PENDING", result.get(1).getStatus());

        // Đảm bảo repository được gọi đúng 1 lần
        verify(paymentRepository, times(1)).findAll();
    }
}
