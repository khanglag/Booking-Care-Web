package com.example.Booking_Care_Web;

public class TestMain {
    public static final String GOOGLE_ID = System.getenv("GOOGLE_CLIENT_ID");
    public static final String  GOOGLE_SECRET = System.getenv("GOOGLE_CLIENT_SECRET");

    public static void main(String[] args) {
        System.out.println(GOOGLE_ID);
        System.out.println(GOOGLE_SECRET);
    }
}
