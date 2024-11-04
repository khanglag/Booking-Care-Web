package com.example.Booking_Care_Web.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Size(max = 7)
    @Column(name = "user_id", nullable = false, length = 7)
    private String userId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 10)
    @NotNull
    @Column(name = "phone_number", nullable = false, length = 10)
    private String phoneNumber;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "gender", nullable = false)
    private String gender;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description")
    private String description;

    @Size(max = 12)
    @Column(name = "identification_card", length = 12)
    private String identificationCard;

    @OneToOne(mappedBy = "user")
    private Account account;

    @OneToMany(mappedBy = "patient")
    private Set<Appointment> appointmentsPatient = new LinkedHashSet<>();

    @OneToMany(mappedBy = "doctor")
    private Set<Appointment> appointmentsDoctor = new LinkedHashSet<>();


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Appointment> getAppointmentsPatient() {
        return appointmentsPatient;
    }

    public void setAppointmentsPatient(Set<Appointment> appointmentsPatient) {
        this.appointmentsPatient = appointmentsPatient;
    }

    public Set<Appointment> getAppointmentsDoctor() {
        return appointmentsDoctor;
    }

    public void setAppointmentsDoctor(Set<Appointment> appointmentsDoctor) {
        this.appointmentsDoctor = appointmentsDoctor;
    }



}