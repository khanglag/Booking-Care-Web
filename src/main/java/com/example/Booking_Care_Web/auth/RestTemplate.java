package com.example.Booking_Care_Web.auth;

import org.springframework.context.annotation.Bean;

public class RestTemplate {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
