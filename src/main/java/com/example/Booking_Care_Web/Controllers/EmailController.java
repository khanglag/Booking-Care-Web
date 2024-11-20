package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Services.AccountServiceImp;
import com.example.Booking_Care_Web.Services.EmailService;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Optional;

@RestController
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
    @ResponseBody
    public String resetPassword(@RequestParam String email) {
        User user = userService.findByEmail(email);
        System.out.println(user);
        if (user == null) {
            return "User not found";
        }
        Account account = accountService.findAccountById(user.getUserId());
        String newPassword = generateNewPassword();
        System.out.println(newPassword);
        accountService.changePassword(account.getUsername(), newPassword);
        emailService.sendSimpleEmail(email, "Password reset successfully", "New password:"+ newPassword);
        return "Password reset successfully";
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
