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

    private final AuthenticationManager authenticationManager;
    public HomeController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

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

    @PostMapping("/login")
    public String login(@RequestParam("phone") String phone,
                        @RequestParam("password") String password, Model model) {

        System.out.println("phone : " + phone);
        System.out.println("password : " + password);

        try {
            // Tạo đối tượng AuthenticationToken với username và mật khẩu
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(phone, password));

            // Đặt Authentication vào SecurityContext để Spring Security nhận diện người dùng đã đăng nhập
            SecurityContextHolder.getContext().setAuthentication(authentication);

            return "redirect:/home"; // Nếu đăng nhập thành công, chuyển hướng đến trang chính

        } catch (Exception e) {
            // Nếu xảy ra lỗi, quay lại trang đăng nhập và hiển thị thông báo lỗi
            model.addAttribute("error", "Tên đăng nhập hoặc mật khẩu không đúng!");
            return "signin";  // trả về trang đăng nhập
        }
    }
}
