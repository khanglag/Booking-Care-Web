package com.example.Booking_Care_Web.Models.Dtos;

import com.example.Booking_Care_Web.Models.enumtype.PaymentMethod;
import com.example.Booking_Care_Web.Models.enumtype.StatusPayment;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTO {
    private Long id;
    private int appointmentId;
    private LocalDateTime paymentDateTime;
    private String paymentMethod;
    private BigDecimal amount;
    private String status;

    @Override
    public String toString() {
        return "PaymentDTO:{"
                + "id=" + id + ", "
                + "appointmentId='" + appointmentId + "', "
                + "paymentDateTime='" + paymentDateTime + "', "
                + "PaymentMethod='" + paymentMethod + "', "
                + "StatusPayment=" + status + ", "
                + "amount='" + amount + "', "
                + "}";
    }
}
