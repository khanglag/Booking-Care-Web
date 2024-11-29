package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.UserDTO;
import com.example.Booking_Care_Web.Models.Entities.Account;

import com.example.Booking_Care_Web.Services.AccountService;
import com.example.Booking_Care_Web.Services.UserService;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.oauth2.core.user.OAuth2User;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserServiceImpl userServiceImpl;

    @RequestMapping("/signin")
    public String signin() {
        return "signin";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/dashboard")
    public String dashboard(HttpSession session, Authentication authentication, Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        System.out.println("============================");
        if (principal instanceof User) {
            String username = ((User) principal).getUsername();
            System.out.println("Username: " + username);
        } else {
            System.out.println("Principal: " + principal.toString());
        }
        // String userId = (String) session.getAttribute("userId");
        // model.addAttribute("name", userId);
        return "dashboard";
    }



    @RequestMapping("/instruct")
    public String instruct() {
        return "instruct";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/profile")
    public String profile() {
        return "profile";
    }

    @RequestMapping("/profile/individual")
    public String individual() {
        return "individual";
    }

    @RequestMapping("/profile/medicalRecord")
    public String medicalRecord() {
        return "medicalRecord";
    }

    @RequestMapping("/profile/appointment")
    public String appointment() {
        return "appointment";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/reset-password")
    public String resetPassword() {
        return "resetpassword";
    }

    //Chỗ sửa username trong account thành name trong user
    @GetMapping("/index")
    public String checkLogin(Model model, Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((User) principal).getUsername();
            session.setAttribute("username", username);
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", username);
        }
        List<UserDTO> doctors = userServiceImpl.findAllDoctors();
        List<List<UserDTO>> doctorGroups = partition(doctors, 4);
        model.addAttribute("doctorGroups", doctorGroups);
        System.out.println(doctorGroups);
        return "index";
    }

    @GetMapping("/")
    public String indexPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        List<UserDTO> doctors = userServiceImpl.findAllDoctors();
        List<List<UserDTO>> doctorGroups = partition(doctors, 4);
        model.addAttribute("doctorGroups", doctorGroups);
        return "index";
    }

    private List<List<UserDTO>> partition(List<UserDTO> list, int size) {
        List<List<UserDTO>> partitions = new ArrayList<>();
        for (int i = 0; i < list.size(); i += size) {
            partitions.add(list.subList(i, Math.min(i + size, list.size())));
        }
        return partitions;
    }
    @GetMapping("/instruct")
    public String instructPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        return "instruct";
    }

    @GetMapping("/contact")
    public String contactPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        return "contact";
    }

    @GetMapping("/profile")
    public String profilePage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        Account account = accountService.findByUsername(username);
        com.example.Booking_Care_Web.Models.Entities.User user = account.getUser();
        model.addAttribute("nameOfUser",user.getName());
        return "profile";
    }

}
