package com.example.Booking_Care_Web.Controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.authentication.AuthenticationManager;

@Controller
public class HomeController {


    @RequestMapping("/signin")
    public String signin() {
        return "signin";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/booking")
    public String booking() {
        return "booking";
    }

    @RequestMapping("/instruct")
    public String instruct() {
        return "instruct";
    }

    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/reset-password")
    public String resetPassword() {return "resetpassword";}



}
