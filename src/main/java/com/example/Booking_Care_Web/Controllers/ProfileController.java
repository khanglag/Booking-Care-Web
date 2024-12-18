package com.example.Booking_Care_Web.Controllers;
import com.example.Booking_Care_Web.Models.Entities.*;
import com.example.Booking_Care_Web.Services.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountServiceImp accountServiceImp;
    @Autowired
    private MedicalRecordService recordService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CheckupPackpageServiceImpl checkupPackpageServiceImpl;

    @GetMapping("/profile/individual")
    public String individualPage(org.springframework.ui.Model model, HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        com.example.Booking_Care_Web.Models.Entities.User user = userServiceImpl.findById(userID);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            return "signin";
        }

        model.addAttribute("userInfo",user);
        model.addAttribute("nameOfUser",user.getName());
        return "individual";
    }
    @PostMapping(value = "/individual",consumes = "application/json")
    public User updateUser(@RequestBody User user, Model model) {
        user.setAddress(user.getAddress());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setIdentificationCard(user.getIdentificationCard());
        user.setGender(user.getGender());
        model.addAttribute("userInfo", user);
        return userServiceImpl.updateUser(user.getUserId(), user);
    }
    @GetMapping("/profile/medicalRecord")
    public String medicalRecordPage(Model model, HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        com.example.Booking_Care_Web.Models.Entities.User user = userServiceImpl.findById(userID);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            return "signin";
        }
        com.example.Booking_Care_Web.Models.Entities.User userPT ;
        com.example.Booking_Care_Web.Models.Entities.User userDT ;
        List<MedicalRecord> medicalRecords = recordService.findMedicalRecordsByPatientId(userID);
        List<Map<String, Object>> medicalRecordData = new ArrayList<>();
        if (medicalRecords != null && !medicalRecords.isEmpty()) {
            for (MedicalRecord record : medicalRecords) {
                Map<String, Object> recordData = new HashMap<>();
                userPT  = userService.findById(userID);
                userDT   = userService.findById(record.getDoctorId());
                recordData.put("id", record.getId());
                recordData.put("patientMedicalRecords",userPT.getName());
                recordData.put("doctorMedicalRecords", userDT.getName());
                recordData.put("description", record.getDescription());
                recordData.put("createAt", record.getCreateAt());
                recordData.put("updateAt", record.getUpdatedAt());
                recordData.put("diagnosis", record.getDiagnosis());
                recordData.put("treatmentPlan",record.getTreatmentPlan());
                medicalRecordData.add(recordData);
            }
        }
        model.addAttribute("medicalRecords", medicalRecordData);
        model.addAttribute("nameOfUser", user.getName());
        return "medicalRecord";
    }
    @GetMapping("/profile/appointment")
    public String appointmentPage(Model model, HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        com.example.Booking_Care_Web.Models.Entities.User user = userServiceImpl.findById(userID);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            return "signin";
        }

        com.example.Booking_Care_Web.Models.Entities.User userPT ;
        com.example.Booking_Care_Web.Models.Entities.User userDT ;
        CheckupPackpage checkupPackpage;
        List<Appointment> appointments= appointmentService.findAppointmentByPatientId(userID);
        List<Map<String, Object>> appointmentData = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (appointments != null && !appointments.isEmpty()) {
            for (Appointment appointment : appointments) {
                Map<String, Object> apmData = new HashMap<>();
                userPT = userService.findById(userID);
                userDT   = userService.findById(appointment.getDoctorId());
                checkupPackpage = checkupPackpageServiceImpl.findById(appointment.getPackageId());
                String availableDatetime = appointment.getAvailableDatetime().format(formatter);
                apmData.put("appointmentId", appointment.getAppointmentId());
                apmData.put("patientAppointment",userPT.getName());
                apmData.put("doctorAppointment", userDT.getName());
                apmData.put("availableDatetime", availableDatetime);
                apmData.put("packageField", checkupPackpage.getName());
                apmData.put("packagePrice",checkupPackpage.getAmount());
                apmData.put("examinationDay", appointment.getExaminationDay());
                apmData.put("timeId", appointment.getTimeId());
                apmData.put("note",appointment.getNote());
                apmData.put("status",appointment.getStatus());
                appointmentData.add(0,apmData);
            }
        }
        model.addAttribute("appointments", appointmentData);
        model.addAttribute("nameOfUser", user.getName());
        return "appointment";
    }

}
