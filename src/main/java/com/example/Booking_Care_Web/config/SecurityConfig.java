//package com.example.Booking_Care_Web.config;
//import com.example.Booking_Care_Web.Services.CustomUserDetailsService;
//import com.example.Booking_Care_Web.auth.CustomAuthenticationSuccessHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
//        this.customUserDetailsService = customUserDetailsService;
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable()) // Nếu cần tắt CSRF, sử dụng cú pháp này
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/signin", "/register","/assets/**","/index","/reset-password").permitAll() // Cấu hình các URL không yêu cầu xác thực
//                        .anyRequest().authenticated() // Tất cả các URL khác đều yêu cầu đăng nhập
//                )
//                .formLogin(form -> form
//                        .loginPage("/signin") // Chỉ định trang đăng nhập
//                        .successHandler(customAuthenticationSuccessHandler)
//                        .permitAll()
//                        .failureUrl("/signin?error")
//                        .loginProcessingUrl("/j_spring_security_check")
//                )
//                .logout(logout -> logout
//                        .logoutUrl("/logout")
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                );
//        return http.build();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
//        return configuration.getAuthenticationManager();
//    }
//
//
//
//
//}

package com.example.Booking_Care_Web.config;

import com.example.Booking_Care_Web.Services.CustomUserDetailsService;
import com.example.Booking_Care_Web.auth.CustomAuthenticationSuccessHandler;
import com.example.Booking_Care_Web.auth.CustomOAuth2SuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

        @Autowired
        private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

        @Autowired
        private CustomUserDetailsService customUserDetailsService;

        @Autowired
        private CustomOAuth2SuccessHandler customOAuth2SuccessHandler;

        public SecurityConfig(CustomUserDetailsService customUserDetailsService,
                        CustomOAuth2SuccessHandler customOAuth2SuccessHandler) {
                this.customUserDetailsService = customUserDetailsService;
                this.customOAuth2SuccessHandler = customOAuth2SuccessHandler;
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(csrf -> csrf.disable()) // Nếu cần tắt CSRF, sử dụng cú pháp này
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers(
                                                                "/signin",
                                                                "/register",
                                                                "/assets/**",
                                                                "/index",
                                                                "/reset-password",
                                                                "/oauth2/**" // Thêm endpoint OAuth2
                                                ).permitAll() // Cấu hình các URL không yêu cầu xác thực
                                                .anyRequest().authenticated() // Tất cả các URL khác đều yêu cầu đăng
                                                                              // nhập
                                )
                                .formLogin(form -> form
                                                .loginPage("/signin") // Chỉ định trang đăng nhập
                                                .successHandler(customAuthenticationSuccessHandler)
                                                .permitAll()
                                                .failureUrl("/signin?error")
                                                .loginProcessingUrl("/j_spring_security_check"))
                                .oauth2Login(oauth2 -> oauth2
                                                .loginPage("/signin") // Trang đăng nhập tùy chỉnh
                                                .successHandler(customOAuth2SuccessHandler)
                                                .failureUrl("/signin?error") // Trang đích khi đăng nhập thất bại
                                )
                                .logout(logout -> logout
                                                .logoutUrl("/logout")
                                                .logoutSuccessUrl("/login?logout")
                                                .permitAll()
                                                .clearAuthentication(true)
                                                .invalidateHttpSession(true)
                                                .deleteCookies("JSESSIONID"));
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
