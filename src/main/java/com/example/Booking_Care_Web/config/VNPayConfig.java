package com.example.Booking_Care_Web.config;

public class VNPayConfig {
    public static final String VNP_URL = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
    public static final String VNP_RETURN_URL = "https://yourdomain.com/vnpay-return";
    public static final String VNP_TMNCODE = "YOUR_TMNCODE"; // Mã TmnCode từ VNPay
    public static final String VNP_HASH_SECRET = "YOUR_HASH_SECRET"; // Mã bí mật
    public static final String VNP_API_VERSION = "2.1.0";
    public static final String VNP_COMMAND = "pay";
}
