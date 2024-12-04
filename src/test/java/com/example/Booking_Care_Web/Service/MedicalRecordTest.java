//package com.example.Booking_Care_Web.Service;
//
//import com.example.Booking_Care_Web.Models.Dtos.MedicalRecordDTO;
//import com.example.Booking_Care_Web.Models.Entities.MedicalRecord;
//import com.example.Booking_Care_Web.Models.Entities.User;
//import com.example.Booking_Care_Web.Services.MedicalRecordServiceImp;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
//
//import java.util.List;
//
//@SpringBootTest
//@SpringJUnitWebConfig
//public class MedicalRecordTest {
//
//    @Autowired
//    MedicalRecordServiceImp medicalRecordServiceImp;
//
//    @Test
//    public void testSaveMedicalRecord() {
//        User patient = new User();
//        patient.setUserId("pt00004");
//        User doctor = new User();
//        doctor.setUserId("doctor1");
//        String description = "Test";
//        String diagnosis = "Test";
//        String treatment = "Test";
//
//        MedicalRecord medicalRecord = MedicalRecord.builder()
//                .patientMedicalRecords(patient)
//                .doctorMedicalRecords(doctor)
//                .diagnosis(diagnosis)
//                .description(description)
//                .treatmentPlan(treatment)
//                .build();
//
//        MedicalRecord medicalRecord1 = medicalRecordServiceImp.saveMedicalRecord(medicalRecord);
//    }
//
//    @Test
//    public void testFindAllMedicalRecord() {
//        List<MedicalRecordDTO> medicalRecords = medicalRecordServiceImp.getAllMedicalRecords();
//        medicalRecords.forEach(medical -> System.out.println(medical));
//    }
//
//    @Test
//    public void testFindMedicalRecordById() {
//        MedicalRecord medicalRecord = medicalRecordServiceImp.findMedicalRecordByMedicalRecordID(2);
//        System.out.println(medicalRecord);
//    }
//
//    @Test
//    public void testFindMedicalRecordByPatientId(){
//        List<MedicalRecord> medicalRecords = medicalRecordServiceImp.findMedicalRecordsByPatientId("pt00004");
//        medicalRecords.forEach(medical -> System.out.println(medical));
//    }
//
//    @Test
//    public void testFindMedicalRecordByDoctorId(){
//        List<MedicalRecord> medicalRecords = medicalRecordServiceImp.findMedicalRecordsByDoctorId("doctor1");
//        medicalRecords.forEach(medical -> System.out.println(medical));
//    }
//
//    @Test
//    public void testUpdate(){
//        Integer medicalRecordId = 2;
//        User patient = new User();
//        patient.setUserId("pt00004");
//        User doctor = new User();
//        doctor.setUserId("doctor1");
//        String description = "hết cú";
//        String diagnosis = "Test";
//        String treatment = "chôn";
//
//        MedicalRecord medicalRecord = new MedicalRecord();
//        medicalRecord.setId(medicalRecordId);
//        medicalRecord.setPatientMedicalRecords(patient);
//        medicalRecord.setDoctorMedicalRecords(doctor);
//        medicalRecord.setDiagnosis(diagnosis);
//        medicalRecord.setTreatmentPlan(treatment);
//        medicalRecord.setDescription(description);
//        medicalRecord.setTreatmentPlan(treatment);
//        System.out.println(medicalRecord);
//        System.out.println(medicalRecordServiceImp.updateMedicalRecord(medicalRecord));
//    }
//}
