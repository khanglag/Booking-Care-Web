package com.example.Booking_Care_Web.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    public void sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom("khangdo0107@gmail.com"); // Email gửi
        mailSender.send(message);
    }

    public void sendNewPasswordEmail(String to, String name, String newPassword) throws MessagingException {
        Context context = new Context();
        context.setVariable("name", name);
        context.setVariable("newPassword", newPassword);

        String emailContent = templateEngine.process("new-password-template", context);
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(to);
        helper.setSubject("Your New Password");
        helper.setText(emailContent, true);
        mailSender.send(mimeMessage);
    }

    public void sendAppointmentConfirmationEmail(String toEmail, String patientName, String appointmentDate, String doctorName, String phoneNumber) throws MessagingException {
        // Tạo context để truyền dữ liệu vào template
        Context context = new Context();
        context.setVariable("patientName", patientName);
        context.setVariable("appointmentDate", appointmentDate);

        context.setVariable("doctorName", doctorName);
        context.setVariable("phoneNumber", phoneNumber);

        // Render template
        String emailContent = templateEngine.process("appointment-confirmation-template", context);

        // Tạo và gửi email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(toEmail);
        helper.setSubject("Appointment Confirmation");
        helper.setText(emailContent, true);

        mailSender.send(message);
    }

}
