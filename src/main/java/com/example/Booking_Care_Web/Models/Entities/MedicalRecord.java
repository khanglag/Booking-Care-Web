package com.example.Booking_Care_Web.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_record")
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private User patientMedicalRecords;

    @NotNull
    @Lob
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private User doctorMedicalRecords;

    @Size(max = 255)
    @NotNull
    @Column(name = "diagnosis", nullable = false)
    private String diagnosis;

    @Size(max = 255)
    @Column(name = "treatment_plan")
    private String treatmentPlan;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @PrePersist
    protected void onCreate() {
        createAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public String getPatientId(){
        return patientMedicalRecords != null ? patientMedicalRecords.getUserId() : null;
    }
    public String getDoctorId(){
        return doctorMedicalRecords != null ? doctorMedicalRecords.getUserId() : null;
    }

    @Override
    public String toString() {
        return "MedicalRecord:{" +
                "recordId='" + id +'\''+
                "patientId='" + patientMedicalRecords.getUserId() +'\''+
                "doctorId='" + doctorMedicalRecords.getUserId() +'\''+
                "description='" + description +'\''+
                "diagnosis='" + diagnosis +'\''+
                "treatmentPlan='" + treatmentPlan +'\''+
                "createdAt='" + createAt +'\''+
                "updatedAt='" + updatedAt +'\'' +
                '}';
    }

}
