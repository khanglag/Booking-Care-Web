package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.MedicalRecordDTO;
import com.example.Booking_Care_Web.Models.Entities.MedicalRecord;
import com.example.Booking_Care_Web.Repositories.MedicalRecordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedicalRecordServiceImp implements MedicalRecordService {
    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Transactional
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Transactional
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        MedicalRecord medicalRecord1 = medicalRecordRepository.findById(medicalRecord.getId()).orElse(null);
        if (medicalRecord1 == null) {
            throw new RuntimeException("Medical Record Not Found with ID: " + medicalRecord.getId());
        }
        medicalRecord1.setPatientMedicalRecords(medicalRecord.getPatientMedicalRecords());
        medicalRecord1.setDoctorMedicalRecords(medicalRecord.getDoctorMedicalRecords());
        medicalRecord1.setDescription(medicalRecord.getDescription());
        medicalRecord1.setDiagnosis(medicalRecord.getDiagnosis());
        medicalRecord1.setTreatmentPlan(medicalRecord.getTreatmentPlan());
        return medicalRecordRepository.save(medicalRecord1);
    }

    @Override
    public List<MedicalRecordDTO> getAllMedicalRecords() {
        return medicalRecordRepository.findAll().stream()
                .map(medicalRecord -> new MedicalRecordDTO(
                        medicalRecord.getId(),
                        medicalRecord.getPatientMedicalRecords().getUserId(),
                        medicalRecord.getDoctorMedicalRecords().getUserId(),
                        medicalRecord.getTreatmentPlan(),
                        medicalRecord.getDiagnosis(),
                        medicalRecord.getDescription(),
                        medicalRecord.getCreateAt(),
                        medicalRecord.getUpdatedAt()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public List<MedicalRecord> findMedicalRecordsByPatientId(String patientId) {
        return medicalRecordRepository.findMedicalRecordsByPatientId(patientId);
    }

    @Override
    public List<MedicalRecord> findMedicalRecordsByDoctorId(String doctorId) {
        return medicalRecordRepository.findMedicalRecordByDoctorId(doctorId);
    }

    @Override
    public MedicalRecord findMedicalRecordByMedicalRecordID(Integer medicalRecordID) {
        return medicalRecordRepository.findById(medicalRecordID).orElse(null);
    }
}
