package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Services.VNPAYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PaymentController {

    @Autowired
    private VNPAYService vnpayService;

    // Trang hiển thị form thanh toán
    @GetMapping("/payment")
    public String showPaymentPage(Model model) {
        // Có thể đưa thông tin người dùng và tổng số tiền vào model
        String total = String.valueOf(2000000);
        model.addAttribute("user", "Nguyễn Văn A");
        model.addAttribute("total", total);  // Ví dụ số tiền thanh toán
        return "orderCreate";  // Trả về view thanh toán (payment.html)
    }

    // Xử lý thanh toán khi người dùng gửi form
    @PostMapping("/submitOrder")
    public String submitOrder(@RequestParam("amount") int amount,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request) {
        // Gọi service VNPAYService để tạo đơn thanh toán
        String paymentUrl = vnpayService.createOrder(request, amount, orderInfo, "http://localhost:8080/paymentReturn");

        // Chuyển hướng đến VNPay để thanh toán
        return "redirect:" + paymentUrl;
    }

    // Xử lý kết quả trả về từ VNPay sau khi thanh toán
    @GetMapping("/paymentReturn")
    public String paymentReturn(HttpServletRequest request, Model model) {
        // Kiểm tra kết quả thanh toán
        int result = vnpayService.orderReturn(request);

        // Tùy thuộc vào kết quả thanh toán, hiển thị thông báo thành công hoặc thất bại
        if (result == 1) {
            model.addAttribute("message", "Thanh toán thành công!");
            return "orderSuccess";  // Trang thông báo thành công
        } else if (result == 0) {
            model.addAttribute("message", "Thanh toán thất bại!");
            return "orderFail";  // Trang thông báo thất bại
        } else {
            model.addAttribute("message", "Có lỗi xảy ra trong quá trình thanh toán!");
            return "orderFail";  // Trang thông báo lỗi
        }
    }
}
