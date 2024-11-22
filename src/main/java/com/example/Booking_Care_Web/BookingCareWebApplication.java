package com.example.Booking_Care_Web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingCareWebApplication {
	public static final String GOOGLE_ID = System.getenv("GOOGLE_CLIENT_ID");
	public static final String  GOOGLE_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");

	public static void main(String[] args) {
		System.out.println(GOOGLE_ID);
		System.out.println(GOOGLE_SECRET);
		SpringApplication.run(BookingCareWebApplication.class, args);
	}

}
