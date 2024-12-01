package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.enumtype.PaymentMethod;

public interface PaymentService {
    String createVNPayPayment(double amount, String orderId, PaymentMethod method) throws Exception;
    void handleVNPayResponse(String responseCode, String orderId);
}
