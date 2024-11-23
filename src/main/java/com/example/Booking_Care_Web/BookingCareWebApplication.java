package com.example.Booking_Care_Web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingCareWebApplication {
	public static final String GOOGLE_ID = System.getenv("FACEBOOK_APP_ID");
	public static final String  GOOGLE_SECRET = System.getenv("FACEBOOK_CLIENT_SECRET");

	public static void main(String[] args) {
		System.out.println("Facebook Client ID: " + GOOGLE_ID);
		System.out.println("Facebook Client Secret: " + GOOGLE_SECRET);
		SpringApplication.run(BookingCareWebApplication.class, args);
	}

}
