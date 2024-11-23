package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {

    @Query("SELECT m FROM MedicalRecord m WHERE m.patientMedicalRecords.userId = :id")
    List<MedicalRecord> findMedicalRecordsByPatientId(String id);

    @Query("SELECT d FROM MedicalRecord d WHERE d.doctorMedicalRecords.userId = :id")
    List<MedicalRecord> findMedicalRecordByDoctorId(String id);
}
