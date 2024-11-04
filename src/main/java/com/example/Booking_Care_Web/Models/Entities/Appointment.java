package com.example.Booking_Care_Web.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @Size(max = 7)
    @Column(name = "appointment_id", nullable = false, length = 7)
    private String appointmentId;

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
    private Instant availableDatetime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id")
    private CheckupPackpage packageField;

    @NotNull
    @Column(name = "examination_day", nullable = false)
    private LocalDate examinationDay;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "time_id", nullable = false)
    private TimeFrame time;

    @Lob
    @Column(name = "note")
    private String note;

    @NotNull
    @Lob
    @Column(name = "status", nullable = false)
    private String status;

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

    public User getDoctor() {
        return doctor;
    }

    public void setDoctor(User doctor) {
        this.doctor = doctor;
    }

    public Instant getAvailableDatetime() {
        return availableDatetime;
    }

    public void setAvailableDatetime(Instant availableDatetime) {
        this.availableDatetime = availableDatetime;
    }

    public CheckupPackpage getPackageField() {
        return packageField;
    }

    public void setPackageField(CheckupPackpage packageField) {
        this.packageField = packageField;
    }

    public LocalDate getExaminationDay() {
        return examinationDay;
    }

    public void setExaminationDay(LocalDate examinationDay) {
        this.examinationDay = examinationDay;
    }

    public TimeFrame getTime() {
        return time;
    }

    public void setTime(TimeFrame time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}