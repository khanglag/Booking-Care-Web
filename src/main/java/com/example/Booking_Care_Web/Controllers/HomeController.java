package com.example.Booking_Care_Web.Controllers;

import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;
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

//    @RequestMapping("/dashboard")
//    public String dashboard(@AuthenticationPrincipal OAuth2User oAuth2User, Model model) {
//        if (oAuth2User != null) {
//            String email = oAuth2User.getAttribute("email");
//            String name = oAuth2User.getAttribute("name");
//            String gender = oAuth2User.getAttribute("gender");
//            // Đưa thông tin vào model để hiển thị
//            model.addAttribute("name", name);
//            model.addAttribute("email", email);
//            model.addAttribute("gender", gender);
//        }
//        return "dashboard";
//    }

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
//        String userId = (String) session.getAttribute("userId");
//        model.addAttribute("name", userId);
        return "dashboard";
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
