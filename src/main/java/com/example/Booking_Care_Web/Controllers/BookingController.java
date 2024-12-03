package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.AppointmentDTO;
import com.example.Booking_Care_Web.Models.Dtos.CheckupPackpageDTO;
import com.example.Booking_Care_Web.Models.Entities.*;
import com.example.Booking_Care_Web.Services.*;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class BookingController {
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

    @RequestMapping("/booking")
    public String booking() {
        return "booking";
    }
    @GetMapping("/booking/{id}")
    public String bookingPage(@PathVariable("id") String doctorId,
                              Model model,
                              HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        com.example.Booking_Care_Web.Models.Entities.User user = userServiceImpl.findById(userID);
        if (user != null) {
            model.addAttribute("user", user);
        } else {
            return "signin";
        }

        model.addAttribute("nameOfUser",user.getName());

        User doctor = userServiceImpl.findById(doctorId);
        model.addAttribute("doctor",doctor);

        List<CheckupPackpageDTO> checkupPackpages = checkupPackpageServiceImpl.findAll();
        model.addAttribute("checkupPackages", checkupPackpages);
        return "booking";
    }
    @PostMapping(value = "/booking",consumes = "application/json")
    public Appointment booking(@RequestBody Map<String, Object> appointmentData,Model model) throws ParseException, MessagingException {
        AppointmentDTO appointment = new AppointmentDTO();

        appointment.setPatient_id((String) appointmentData.get("patient"));
        appointment.setDoctor_id((String) appointmentData.get("doctor"));
        appointment.setPackage_id((String) appointmentData.get("packageField"));
        appointment.setTime_id((String) appointmentData.get("time"));
        String availableDatetimeStr = (String) appointmentData.get("availableDatetime");
        appointment.setAvailable_datetime(LocalDate.parse(availableDatetimeStr));
        String examinationDayStr = (String) appointmentData.get("examinationDay");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        appointment.setExamination_date(dateFormat.parse(examinationDayStr));
        appointment.setNote((String) appointmentData.get("note"));
        appointment.setStatus((String) appointmentData.get("status"));
        Appointment apm = new Appointment();

        User patient = userService.findById(appointment.getPatient_id());
        User doctor = userService.findById(appointment.getDoctor_id());
//       emailService.sendAppointmentConfirmationEmail(patient.getEmail(),patient.getName(),examinationDayStr, doctor.getName(),doctor.getPhoneNumber());
        CheckupPackpage packageField = checkupPackpageServiceImpl.findById(appointment.getPackage_id());
        TimeFrame timeFrame = timeFrameServiceImpl.findById(appointment.getTime_id());
        apm.setPatient(patient);
        apm.setDoctor(doctor);
        apm.setPackageField(packageField);
        apm.setTime(timeFrame);
        apm.setAvailableDatetime(appointment.getAvailable_datetime());
        apm.setExaminationDay(appointment.getExamination_date());
        apm.setNote(appointment.getNote());
        apm.setStatus(appointment.getStatus());
        model.addAttribute("doctor", doctor);
        model.addAttribute("user", patient);
       return appointmentServiceImpl.save(apm);

    }

}
