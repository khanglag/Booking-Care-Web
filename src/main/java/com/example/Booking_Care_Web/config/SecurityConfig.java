package com.example.Booking_Care_Web.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Nếu cần tắt CSRF, sử dụng cú pháp này
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/signin", "/register").permitAll() // Cấu hình các URL không yêu cầu xác thực
                        .anyRequest().authenticated() // Tất cả các URL khác đều yêu cầu đăng nhập
                )
                .formLogin(form -> form
                        .loginPage("/signin") // Chỉ định trang đăng nhập
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/index", true) // Đường dẫn khi đăng nhập thành công
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
