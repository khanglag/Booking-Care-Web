package com.example.Booking_Care_Web.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "time_frame")
public class TimeFrame {
    @Id
    @Size(max = 7)
    @Column(name = "time_id", nullable = false, length = 7)
    private String timeId;

    @NotNull
    @Column(name = "time_start", nullable = false)
    private LocalTime timeStart;

    @NotNull
    @Column(name = "time_end", nullable = false)
    private LocalTime timeEnd;

    @OneToMany(mappedBy = "time")
    private Set<Appointment> appointments = new LinkedHashSet<>();

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

}