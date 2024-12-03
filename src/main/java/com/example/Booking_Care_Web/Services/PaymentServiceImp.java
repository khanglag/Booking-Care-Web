package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.PaymentDTO;
import com.example.Booking_Care_Web.Models.Entities.Payment;
import com.example.Booking_Care_Web.Repositories.PaymentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public List<PaymentDTO> findAll() {
        return paymentRepository.findAll().stream()
                .map(payment -> new PaymentDTO(
                        payment.getId(),
                        payment.getAppointment()!= null ? payment.getAppointment().getAppointmentId() : 0,
                        payment.getPayment_time(),
                        payment.getPayment_method().name(),
                        payment.getAmount(),
                        payment.getStatus().name()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<Payment> findByAppointmentId(int id) {
        return paymentRepository.findByAppointmentId(id);
    }

    @Transactional
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Transactional
    public Payment updatePayment(Payment payment) {
        Payment payment1 = paymentRepository.findById(payment.getId()).orElse(null);
        if (payment1 != null) {
            throw new RuntimeException("Payment not found with ID: " + payment.getId());
        }
        payment1.setId(payment.getId());
        payment1.setAppointment(payment.getAppointment());
        payment1.setPayment_time(payment.getPayment_time());
        payment1.setPayment_method(payment.getPayment_method());
        payment1.setAmount(payment.getAmount());
        payment1.setStatus(payment.getStatus());
        return paymentRepository.save(payment1);
    }

}
