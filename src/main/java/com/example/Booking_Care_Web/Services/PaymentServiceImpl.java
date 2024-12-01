package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.config.VNPayConfig;
import com.example.Booking_Care_Web.Models.Dtos.PaymentDTO;
import com.example.Booking_Care_Web.Models.enumtype.PaymentMethod;
import com.example.Booking_Care_Web.Models.enumtype.StatusPayment;
import com.example.Booking_Care_Web.Repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public String createVNPayPayment(double amount, String orderId, PaymentMethod method) throws Exception {
        if (amount <= 0 || orderId == null || orderId.isEmpty() || VNPayConfig.VNP_RETURN_URL == null) {
            throw new IllegalArgumentException("Invalid payment parameters");
        }

        Long orderIdLong;
        try {
            orderIdLong = Long.valueOf(orderId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid order ID format");
        }

        PaymentDTO payment = new PaymentDTO(
                orderIdLong,
                amount,
                method,
                StatusPayment.PENDING,
                LocalDateTime.now()
        );
        paymentRepository.save(payment);

        Map<String, String> vnpParams = new HashMap<>();
        vnpParams.put("vnp_Version", VNPayConfig.VNP_API_VERSION);
        vnpParams.put("vnp_Command", VNPayConfig.VNP_COMMAND);
        vnpParams.put("vnp_TmnCode", VNPayConfig.VNP_TMNCODE);
        vnpParams.put("vnp_Amount", String.valueOf((int) (amount * 100)));
        vnpParams.put("vnp_CurrCode", "VND");
        vnpParams.put("vnp_TxnRef", orderId);
        vnpParams.put("vnp_OrderInfo", "Thanh toan don hang #" + orderId);
        vnpParams.put("vnp_ReturnUrl", VNPayConfig.VNP_RETURN_URL);
        vnpParams.put("vnp_CreateDate", new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));

        List<String> fieldNames = new ArrayList<>(vnpParams.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String value = vnpParams.get(fieldName);
            if (value != null && !value.isEmpty()) {
                hashData.append(fieldName).append('=').append(value).append('&');
                query.append(URLEncoder.encode(fieldName, StandardCharsets.UTF_8))
                        .append('=')
                        .append(URLEncoder.encode(value, StandardCharsets.UTF_8))
                        .append('&');
            }
        }
        hashData.deleteCharAt(hashData.length() - 1);
        query.deleteCharAt(query.length() - 1);

        String secureHash = hmacSHA512(VNPayConfig.VNP_HASH_SECRET, hashData.toString());
        query.append("&vnp_SecureHash=").append(secureHash);

        return VNPayConfig.VNP_URL + "?" + query;
    }

    @Override
    public void handleVNPayResponse(String responseCode, String orderId) {
        logger.info("Handling VNPay response for order ID: {}", orderId);

        // Kiểm tra và chuyển đổi orderId sang Long
        Long orderIdLong;
        try {
            orderIdLong = Long.valueOf(orderId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Order ID is not a valid number: " + orderId, e);
        }

        // Tìm kiếm PaymentDTO trong repository
        PaymentDTO payment = paymentRepository.findById(orderIdLong)
                .orElseThrow(() -> new IllegalArgumentException("Order ID not found: " + orderId));

        // Cập nhật trạng thái thanh toán dựa trên responseCode
        if ("00".equals(responseCode)) {
            payment.setStatus(StatusPayment.SUCCESS);
        } else if ("01".equals(responseCode)) {
            payment.setStatus(StatusPayment.FAILURE);
        } else {
            throw new IllegalArgumentException("Invalid VNPay response code: " + responseCode);
        }

        // Cập nhật lại repository
        paymentRepository.update(payment);
    }

    private String hmacSHA512(String key, String data) throws Exception {
        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA512");
        javax.crypto.spec.SecretKeySpec secretKey = new javax.crypto.spec.SecretKeySpec(key.getBytes(), "HmacSHA512");
        mac.init(secretKey);
        byte[] hash = mac.doFinal(data.getBytes(StandardCharsets.UTF_8));
        StringBuilder result = new StringBuilder();
        for (byte b : hash) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
