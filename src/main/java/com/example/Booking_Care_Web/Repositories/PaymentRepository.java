package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Dtos.PaymentDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class PaymentRepository {
    private final List<PaymentDTO> payments = new ArrayList<>(); // Dữ liệu giả lập

    public List<PaymentDTO> findAll() {
        return new ArrayList<>(payments); // Trả về danh sách tất cả thanh toán
    }

    public Optional<PaymentDTO> findById(Long id) {
        return payments.stream().filter(payment -> payment.getId().equals(id)).findFirst();
    }

    public void save(PaymentDTO payment) {
        payments.add(payment); // Thêm một thanh toán mới vào danh sách
    }

    public void update(PaymentDTO updatedPayment) {
        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getId().equals(updatedPayment.getId())) {
                payments.set(i, updatedPayment);
                return;
            }
        }
        throw new IllegalArgumentException("Payment ID not found.");
    }
}
