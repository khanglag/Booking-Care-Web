package com.example.Booking_Care_Web.Models.Dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicalRecordDTO {
    private int recordId;
    private String patientId;
    private String doctorId;
    private String description;
    private String diagnosis;
    private String treatmentPlan;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Override
    public String toString() {
        return "MedicalRecord:{" +
                "recordId='" + recordId +'\''+
                "patientId='" + patientId +'\''+
                "doctorId='" + doctorId +'\''+
                "description='" + description +'\''+
                "diagnosis='" + diagnosis +'\''+
                "treatmentPlan='" + treatmentPlan +'\''+
                "createdAt='" + createdAt +'\''+
                "updatedAt='" + updatedAt +'\''+
                '}';
    }
}
