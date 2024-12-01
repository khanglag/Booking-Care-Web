package com.example.Booking_Care_Web.Models.Dtos;

import com.example.Booking_Care_Web.Models.enumtype.PaymentMethod;
import com.example.Booking_Care_Web.Models.enumtype.StatusPayment;
import java.time.LocalDateTime;

public class PaymentDTO {
    private Long id;
    private double amount;
    private PaymentMethod method;
    private StatusPayment status;
    private LocalDateTime paymentDate;

    // Constructors
    public PaymentDTO() {}

    public PaymentDTO(Long id, double amount, PaymentMethod method, StatusPayment status, LocalDateTime paymentDate) {
        this.id = id;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public StatusPayment getStatus() {
        return status;
    }

    public void setStatus(StatusPayment status) {
        this.status = status;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", method=" + method +
                ", status=" + status +
                ", paymentDate=" + paymentDate +
                '}';
    }
}
