package com.example.Booking_Care_Web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingCareWebApplication {

	public static void main(String[] args) {
		System.out.println("GOOGLE_CLIENT_ID: " + System.getenv("GOOGLE_CLIENT_ID"));
		System.out.println("GOOGLE_CLIENT_SECRET: " + System.getenv("GOOGLE_CLIENT_SECRET"));

		SpringApplication.run(BookingCareWebApplication.class, args);
	}

}
