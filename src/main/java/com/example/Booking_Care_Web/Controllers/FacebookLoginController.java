package com.example.Booking_Care_Web.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacebookLoginController {

    // Endpoint để hiển thị nút đăng nhập
    @GetMapping("/login")
    public String login() {
        return "Đăng nhập với Facebook bằng cách nhấn vào <a href='/oauth2/authorization/facebook'>đây</a>.";
    }

    // Endpoint để hiển thị thông tin sau khi đăng nhập thành công
    @GetMapping("/home")
    public String home(@AuthenticationPrincipal OAuth2User oAuth2User) {
        if (oAuth2User == null) {
            return "Bạn chưa đăng nhập.";
        }
        String name = oAuth2User.getAttribute("name");
        String email = oAuth2User.getAttribute("email");
        return "Chào mừng, " + name + "! Email của bạn: " + email;
    }

    // Endpoint để xử lý đăng xuất
    @GetMapping("/logout")
    public String logout() {
        return "Bạn đã đăng xuất thành công!";
    }
}
