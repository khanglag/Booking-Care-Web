package com.example.Booking_Care_Web.Models.Entities;

import com.example.Booking_Care_Web.Models.enumtype.PaymentMethod;
import com.example.Booking_Care_Web.Models.enumtype.StatusPayment;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", referencedColumnName = "appointment_id", nullable = true)
    private Appointment appointment;

    @Enumerated(EnumType.STRING) // Lưu chuỗi của enum PaymentMethod trong cơ sở dữ liệu
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod payment_method;

    private BigDecimal amount;

    private LocalDateTime payment_time;

    @Enumerated(EnumType.STRING) // Lưu chuỗi của enum StatusPayment trong cơ sở dữ liệu
    @Column(name = "status", nullable = false)
    private StatusPayment status;

    @PrePersist
    public void onCreate() {
        this.payment_time = LocalDateTime.now();
    }
    public Integer getAppointmentId(){
        return appointment != null ? appointment.getAppointmentId() : null;
    }
}
//double value = 0.1;
//BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
