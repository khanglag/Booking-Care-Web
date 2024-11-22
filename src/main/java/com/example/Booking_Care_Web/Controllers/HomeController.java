package com.example.Booking_Care_Web.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/reset-password")
    public String resetPassword() {return "resetpassword";}

    @GetMapping("/index")
    public String checkLogin(Model model, Authentication authentication, HttpSession session) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
           String username = ((User)principal).getUsername();
            session.setAttribute("username", username);
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", username);
        }
        return "index";
    }
    @GetMapping("/instruct")
    public String instructPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
           return "signin";
        }
        return "instruct"; // Trả về trang profile
    }
    @GetMapping("/contact")
    public String contactPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        return "contact"; // Trả về trang profile
    }
    @GetMapping("/")
    public String indexPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        return "index"; // Trả về trang profile
    }
    @GetMapping("/profile")
    public String profilePage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        return "profile"; // Trả về trang profile
    }

    @GetMapping("/profile/individual")
    public String individualPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        return "individual"; // Trả về trang profile
    }
    @GetMapping("/profile/medicalRecord")
    public String medicalRecordPage(Model model, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        return "medicalRecord"; // Trả về trang profile
    }
}
