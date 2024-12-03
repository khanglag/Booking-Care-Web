package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Entities.Account;
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
        return "doctorUpdateMedicalRecord";
    }

    @PostMapping(value = "/doctorProfile",consumes = "application/json")
    public com.example.Booking_Care_Web.Models.Entities.User updateUser(@RequestBody com.example.Booking_Care_Web.Models.Entities.User user, Model model) {
        user.setAddress(user.getAddress());
        user.setPhoneNumber(user.getPhoneNumber());
        user.setIdentificationCard(user.getIdentificationCard());
        user.setGender(user.getGender());
        user.setDescription(user.getDescription());
        System.out.println("==================================="+user);
        model.addAttribute("userInfo", user);
        return userServiceImpl.updateUser(user.getUserId(), user);
    }
}
