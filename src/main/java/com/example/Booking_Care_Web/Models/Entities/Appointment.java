package com.example.Booking_Care_Web.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id", nullable = false, length = 7)
    private Integer appointmentId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", referencedColumnName = "user_id", nullable = false)
    private User patient;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", referencedColumnName = "user_id", nullable = false)
    private User doctor;

    @NotNull
    @Column(name = "available_datetime", nullable = false)
    private LocalDate availableDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", referencedColumnName = "package_id", nullable = true)
    private CheckupPackpage packageField;

    @NotNull
    @Column(name = "examination_day", nullable = false)
    private Date examinationDay;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "time_id",referencedColumnName = "time_id", nullable = false)
    private TimeFrame time;

    @Lob
    @Column(name = "note")
    private String note;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    public String getPatientId(){
        return patient != null ? patient.getUserId() : null;
    }

    public String getDoctorId(){
        return doctor != null ? doctor.getUserId() : null;
    }

    public String getPackageId(){
        return packageField != null ? packageField.getPackageId() : null;
    }

    public String getTimeId(){
        return time != null ? time.getTimeId() : null;
    }

    @Override
    public String toString(){
        return "Appointment{ " +
                "id='" + appointmentId + '\'' +
                ", patient='" + patient.getUserId() + '\'' +
                ", doctor='" + doctor.getUserId() + '\'' +
                ", available date time='" + availableDatetime + '\'' +
                ", package id='" + packageField.getPackageId() + '\'' +
                ", examination_day='" + examinationDay + '\'' +
                ", time id='" + time.getTimeId() + '\'' +
                ", note='" + note + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}