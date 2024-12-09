package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.MedicalRecordDTO;
import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Models.Entities.MedicalRecord;
import com.example.Booking_Care_Web.Services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DoctorController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CheckupPackpageServiceImpl checkupPackpageServiceImpl;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppointmentServiceImpl appointmentServiceImpl;

    @Autowired
    private CheckupPackpageServiceImpl checkuppackpageServiceImpl;

    @Autowired
    private TimeFrameServiceImpl timeFrameServiceImpl;

    @Autowired
    private MedicalRecordServiceImp medicalRecordServiceImpl;

    @Autowired
    private MedicalRecordService medicalRecordService;
    @GetMapping("/doctorPage")
    public String gdoctorPage(Model model, Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        return "doctorPage";
    }
    @GetMapping("/doctorPage/doctorProfile")
    public String gdoctorProfile(Model model, Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        String userID = (String) session.getAttribute("userID");
        com.example.Booking_Care_Web.Models.Entities.User user = userServiceImpl.findById(userID);
        model.addAttribute("userInfo",user);
        model.addAttribute("nameOfUser",user.getName());
        return "doctorProfile";
    }
    @GetMapping("/doctorPage/doctorUpdateMedicalRecord")
    public String gdoctorUpdateMedicalRecord(Model model, Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        String userID = (String) session.getAttribute("userID");
        com.example.Booking_Care_Web.Models.Entities.User user = userServiceImpl.findById(userID);
        model.addAttribute("userInfo",user);
        model.addAttribute("nameOfUser",user.getName());

        com.example.Booking_Care_Web.Models.Entities.User userPT ;
        com.example.Booking_Care_Web.Models.Entities.User userDT ;
      //  List<MedicalRecord> medicalRecords = medicalRecordServiceImpl.findMedicalRecordsByDoctorIdAndIncompleteFields(userID);
        List<MedicalRecordDTO> medicalRecords = medicalRecordService.getAllMedicalRecords();
        List<Map<String, Object>> medicalRecordData = new ArrayList<>();
        if (medicalRecords != null && !medicalRecords.isEmpty()) {
            for (MedicalRecordDTO record : medicalRecords) {
                Map<String, Object> recordData = new HashMap<>();
                userPT  = userService.findById(record.getPatientId());
                userDT   = userService.findById(record.getDoctorId());
                recordData.put("id", record.getRecordId());
                recordData.put("patientID", userPT.getUserId());
                recordData.put("doctorID", userDT.getUserId());
                recordData.put("patientMedicalRecords",userPT.getName());
                recordData.put("doctorMedicalRecords", userDT.getName());
                recordData.put("description", record.getDescription());
                recordData.put("createAt", record.getCreatedAt());
                recordData.put("updateAt", record.getUpdatedAt());
                recordData.put("diagnosis", record.getDiagnosis());
                recordData.put("treatmentPlan",record.getTreatmentPlan());
                medicalRecordData.add(recordData);
            }
        }
        model.addAttribute("medicalRecords", medicalRecordData);
        System.out.println(medicalRecordData);
        return "doctorUpdateMedicalRecord";
    }

    @PostMapping(value = "/doctorProfile",consumes = "application/json")
    public com.example.Booking_Care_Web.Models.Entities.User updateUser(@RequestBody com.example.Booking_Care_Web.Models.Entities.User user, Model model) {
        user.setAddress(user.getAddress());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setIdentificationCard(user.getIdentificationCard());
        user.setGender(user.getGender());
        user.setDescription(user.getDescription());
        model.addAttribute("userInfo", user);
        return userServiceImpl.updateUser(user.getUserId(), user);
    }

    @PostMapping(value = "/updateMedicalRecord",consumes = "application/json")
    public String updateMedicalRecord(@RequestBody Map<String, Object> medicalRecordData, Model model) {

        String recordId = (String) medicalRecordData.get("recordId");
        String patientID = (String) medicalRecordData.get("patientID");
        String doctorID = (String) medicalRecordData.get("doctorID");
        String createAt = (String) medicalRecordData.get("createAt");
        String description = (String) medicalRecordData.get("description");
        String diagnosis = (String) medicalRecordData.get("diagnosis");
        String treatmentPlan = (String) medicalRecordData.get("treatmentPlan");
        MedicalRecord medicalRecord = new MedicalRecord();
        com.example.Booking_Care_Web.Models.Entities.User patient = userServiceImpl.findById(patientID);
        com.example.Booking_Care_Web.Models.Entities.User doctor = userServiceImpl.findById(doctorID);
        medicalRecord.setId(Integer.parseInt(recordId));
        medicalRecord.setPatientMedicalRecords(patient);
        medicalRecord.setDoctorMedicalRecords(doctor);
        medicalRecord.setDiagnosis(diagnosis);
        medicalRecord.setTreatmentPlan(treatmentPlan);
        medicalRecord.setUpdatedAt(LocalDateTime.parse(createAt));
        medicalRecord.setDescription(description);
        medicalRecord.setUpdatedAt(LocalDateTime.now());
        medicalRecordServiceImpl.updateMedicalRecord(medicalRecord);
        return "doctorUpdateMedicalRecord";
    }
}
