package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Services.VNPAYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentController {

    @Autowired
    private VNPAYService vnpayService;

    /**
     * Tạo thanh toán và trả về URL để chuyển hướng người dùng đến VNPay
     * @param request HttpServletRequest
     * @param amount Số tiền thanh toán (VNĐ)
     * @param orderInfo Thông tin đơn hàng
     * @return URL thanh toán VNPay
     */
    @GetMapping("/payment")
    public Map<String, Object> createPayment(HttpServletRequest request,
                                             @RequestParam int amount,
                                             @RequestParam String orderInfo) {
        String returnUrl = "https://yourdomain.com/vnpay_return"; // Thay bằng URL trả về thực tế
        String paymentUrl = vnpayService.createOrder(request, amount, orderInfo, returnUrl);

        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("paymentUrl", paymentUrl);
        return response;
    }

    /**
     * Xử lý phản hồi từ VNPay khi người dùng hoàn tất thanh toán
     * @param request HttpServletRequest
     * @return Trạng thái giao dịch
     */
    @GetMapping("/vnpay_return")
    public Map<String, Object> handlePaymentReturn(HttpServletRequest request) {
        int result = vnpayService.orderReturn(request);

        Map<String, Object> response = new HashMap<>();
        switch (result) {
            case 1:
                response.put("status", "success");
                response.put("message", "Giao dịch thành công!");
                break;
            case 0:
                response.put("status", "failure");
                response.put("message", "Giao dịch thất bại!");
                break;
            default:
                response.put("status", "error");
                response.put("message", "Chữ ký không hợp lệ!");
                break;
        }
        return response;
    }
}
