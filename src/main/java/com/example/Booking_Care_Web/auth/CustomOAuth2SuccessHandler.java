package com.example.Booking_Care_Web.auth;

import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomOAuth2SuccessHandler implements AuthenticationSuccessHandler {

    private UserServiceImpl userService;
    @Autowired
    public CustomOAuth2SuccessHandler(UserServiceImpl userService) {
        this.userService = userService;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        // Xử lý logic thêm, ví dụ: lưu người dùng vào cơ sở dữ liệu
        if(email != null){
            User checkUser = userService.findByEmail(email);
            if (checkUser == null) {
                User user = new User();
                user.setUserId(userService.createNewUserId("pt"));
                user.setEmail(email);
                user.setName(name);

                userService.saveUser(user);
            }
        }

        response.sendRedirect("/dashboard"); // Chuyển hướng đến trang dashboard
    }
}
