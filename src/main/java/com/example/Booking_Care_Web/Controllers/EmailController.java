package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Services.AccountServiceImp;
import com.example.Booking_Care_Web.Services.EmailService;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Optional;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AccountServiceImp accountService;

    @GetMapping("/send-email")
    public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        emailService.sendSimpleEmail(to, subject, body);
        return "Email sent successfully to " + to;
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String email, Model model)  {
        try {
            User user = userService.findByEmail(email);
            if (user == null) {
                model.addAttribute("message", "User not found");
                return "reset-password-failed";
            }else {
                Account account = accountService.findAccountById(user.getUserId());
                if (account == null) {
                    model.addAttribute("message", "Người dùng không có tài khoản");
                    return "reset-password-failed";
                }
                String newPassword = generateNewPassword();
                accountService.changePassword(account.getUsername(), newPassword);
                emailService.sendNewPasswordEmail(email,user.getName(),newPassword);
                return "reset-password-success";
            }

        }catch (MessagingException e) {
            e.printStackTrace();
            model.addAttribute("message", "Email không thể gửi được");
            return "reset-password-failed";
        }
    }

    private static String generateNewPassword() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }
        return sb.toString();
    }


}
