package com.example.Booking_Care_Web.Models.Dtos;

import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentDTO {
    private int appointmentId;  // ID của cuộc hẹn
    private String patient_id;  // ID của bệnh nhân
    private String doctor_id;   // ID của bác sĩ
    private LocalDate available_datetime;  // Ngày giờ có sẵn cho cuộc hẹn
    private String package_id;  // ID của gói khám
    private Date examination_date;  // Ngày khám
    private String time_id;  // ID của khung giờ
    private String note;  // Ghi chú
    private String status;  // Trạng thái của cuộc hẹn

    @Override
    public String toString() {
        return "AppointmentDTO{"
                + "appointmentId=" + appointmentId + ", "
                + "patient_id='" + patient_id + "', "
                + "doctor_id='" + doctor_id + "', "
                + "available_datetime=" + available_datetime + ", "
                + "package_id='" + package_id + "', "
                + "examination_date=" + examination_date + ", "
                + "time_id='" + time_id + "', "
                + "note='" + note + "', "
                + "status='" + status + "'"
                + "}";
    }
}
