package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Models.Entities.Appointment;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SupportController {
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
    private AppointmentService appointmentService;

    @Autowired
    private CheckupPackpageServiceImpl checkuppackpageServiceImpl;

    @Autowired
    private TimeFrameServiceImpl timeFrameServiceImpl;

    @GetMapping("/supportPage/supportProfile")
    public String gsupportProfile(Model model, Authentication authentication, HttpSession session) {
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
        return "supportProfile";
    }
    @GetMapping("/supportPage")
    public String gsupportPage(Model model, Authentication authentication, HttpSession session) {
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
        return "supportPage";
    }
    @GetMapping("/supportPage/supportReview")
    public String gsupportReview(Model model, Authentication authentication, HttpSession session) {
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
        List<Appointment> appointments = appointmentServiceImpl.findApppontmentByStatus("CHƯA DUYỆT");
        com.example.Booking_Care_Web.Models.Entities.User userPT ;
        com.example.Booking_Care_Web.Models.Entities.User userDT ;
        CheckupPackpage checkupPackpage;

        List<Map<String, Object>> appointmentData = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        if (appointments != null && !appointments.isEmpty()) {
            for (Appointment appointment : appointments) {
                Map<String, Object> apmData = new HashMap<>();
                userPT = userService.findById(appointment.getPatientId());
                userDT   = userService.findById(appointment.getDoctorId());
                checkupPackpage = checkupPackpageServiceImpl.findById(appointment.getPackageId());
                String availableDatetime = appointment.getAvailableDatetime().format(formatter);
                apmData.put("appointmentId", appointment.getAppointmentId());
                apmData.put("patientAppointment",userPT.getName());
                apmData.put("doctorAppointment", userDT.getName());
                apmData.put("availableDatetime", availableDatetime);
                apmData.put("packageField", checkupPackpage.getName());
                apmData.put("examinationDay", appointment.getExaminationDay());
                apmData.put("timeId", appointment.getTimeId());
                apmData.put("note",appointment.getNote());
                apmData.put("status",appointment.getStatus());
                appointmentData.add(0,apmData);
            }
        }
        model.addAttribute("appointments", appointmentData);
        return "supportReview";
    }


    @PostMapping(value = "/supportProfile",consumes = "application/json")
    public com.example.Booking_Care_Web.Models.Entities.User updateUser(@RequestBody com.example.Booking_Care_Web.Models.Entities.User user, Model model) {
        user.setAddress(user.getAddress());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setIdentificationCard(user.getIdentificationCard());
        user.setGender(user.getGender());
        model.addAttribute("userInfo", user);
        return userServiceImpl.updateUser(user.getUserId(), user);
    }


    @PostMapping(value = "/supportReview",consumes = "application/json")
    public String updateApproveAppointment(@RequestBody Map<String, Object> requestData, Model model) {
        String id = (String) requestData.get("id");
        int intId = Integer.parseInt(id);
       Appointment appointment = new Appointment();
        appointment = appointmentService.findAppointmentById(intId);
         appointment.setStatus("ĐÃ DUYỆT");
        appointmentServiceImpl.updateAppointment(appointment);
        return "supportReview";
    }


    @PostMapping(value = "/supportCancel",consumes = "application/json")
    public String updateCancelAppointment(@RequestBody Map<String, Object> requestData, Model model) {
        String id = (String) requestData.get("id");
        int intId = Integer.parseInt(id);
        Appointment appointment = new Appointment();
        appointment = appointmentService.findAppointmentById(intId);
        appointment.setStatus("ĐÃ HỦY");
        appointmentServiceImpl.updateAppointment(appointment);
        return "supportReview";
    }

}
