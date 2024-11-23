package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.MedicalRecordDTO;
import com.example.Booking_Care_Web.Models.Entities.MedicalRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MedicalRecordService {
    List<MedicalRecordDTO> getAllMedicalRecords();
    List<MedicalRecord> findMedicalRecordsByPatientId(String patientId);
    List<MedicalRecord> findMedicalRecordsByDoctorId(String doctorId);
    MedicalRecord findMedicalRecordByMedicalRecordID(Integer medicalRecordID);
}
