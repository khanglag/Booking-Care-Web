package com.example.Booking_Care_Web.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // Kiểm tra quyền của người dùng
        for (GrantedAuthority authority : authorities) {
            String role = authority.getAuthority();
            System.out.println(role);

            // Điều hướng theo Role
            if (role.equals("ROLE_Admin")) {
                response.sendRedirect("/booking");
                return;
            } else if (role.equals("ROLE_Doctor")) {
                response.sendRedirect("/contact");
                return;
            } else if (role.equals("ROLE_Patient")) {
                response.sendRedirect("/index");
                return;
            } else if (role.equals("ROLE_Supporter")) {
                response.sendRedirect("/instruct");
                return;
            }
        }

        // Mặc định nếu không có Role
        response.sendRedirect("/access-denied");
    }
}
