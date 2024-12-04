package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Entities.Payment;
import com.example.Booking_Care_Web.Models.enumtype.PaymentMethod;
import com.example.Booking_Care_Web.Models.enumtype.StatusPayment;
import com.example.Booking_Care_Web.Services.PaymentService;
import com.example.Booking_Care_Web.Services.PaymentServiceImp;
import com.example.Booking_Care_Web.Services.VNPAYService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class PaymentController {

    @Autowired
    private VNPAYService vnpayService;

    @Autowired
    private PaymentServiceImp paymentService;

//    // Trang hiển thị form thanh toán
//    @GetMapping("/payment")
//    public String showPaymentPage( Model model) {
//        // Có thể đưa thông tin người dùng và tổng số tiền vào model
//        String total = String.valueOf(2000000);
//        model.addAttribute("user", "Nguyễn Văn A");
//        model.addAttribute("total", total);  // Ví dụ số tiền thanh toán
//        return "orderCreate";  // Trả về view thanh toán (payment.html)
//    }
//    @PostMapping("/payment")
//    public String processPayment(@RequestBody Map<String, Object> requestData, Model model) {
//        // Lấy dữ liệu từ request body
//        String name = (String) requestData.get("name");
//        String price = (String) requestData.get("price");
//
//        // Xử lý logic thanh toán (ví dụ thêm dữ liệu vào model)
//        model.addAttribute("name", name);
//        model.addAttribute("price", price);
//
//        // Giả định số tiền thanh toán
//        String total = String.valueOf(2000000);
//        model.addAttribute("user", "Nguyễn Văn A");
//        model.addAttribute("total", total);
//
//        return "orderCreate"; // Trả về trang thanh toán
//    }
@PostMapping("/payment")
public String processPayment(
        @RequestParam("name") String name,
        @RequestParam("price") String price,
        Model model) {
    try {
        // Chuyển price từ String sang Double để xử lý số có phần thập phân
        double priceValue = Double.parseDouble(price);

        // Chuyển giá trị thành số nguyên (làm tròn xuống)
        int total = (int) priceValue; // Bạn có thể làm tròn theo nhu cầu, ví dụ: Math.round(priceValue)
        System.out.println("========================="+ total); // In giá trị ra console để kiểm tra

        // Thêm thông tin vào model để hiển thị trên trang thanh toán
        model.addAttribute("user", "Nguyễn Văn A");
        model.addAttribute("total", total);

    } catch (NumberFormatException e) {
        System.out.println("Error parsing price: " + e.getMessage());
        // Handle invalid number input here (e.g., return an error message to the user)
    }

    return "orderCreate";
}
    // Xử lý thanh toán khi người dùng gửi form
    @PostMapping("/submitOrder")
    public String submitOrder(@RequestParam("amount") int amount,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request) {
        // Gọi service VNPAYService để tạo đơn thanh toán
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        String paymentUrl = vnpayService.createOrder(request, amount, orderInfo, baseUrl);

//         Lưu appointmentId đẻ tạo payment
//        @RequestParam("appointmentId") Long appointmentId;
//        request.getSession().setAttribute("appointmentId", appointmentId);

        // Chuyển hướng đến VNPay để thanh toán
        return "redirect:" + paymentUrl;
    }

    // Xử lý kết quả trả về từ VNPay sau khi thanh toán
    @GetMapping("/paymentReturn")
    public String paymentReturn(HttpServletRequest request, Model model) {
        // Kiểm tra kết quả thanh toán
        int result = vnpayService.orderReturn(request);
        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPriceStr = request.getParameter("vnp_Amount");

        BigDecimal totalPrice = new BigDecimal(totalPriceStr).divide(new BigDecimal(100)); // Chia 100 để về đúng đơn vị


        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        Payment payment = new Payment();

        // Tùy thuộc vào kết quả thanh toán, hiển thị thông báo thành công hoặc thất bại
        if (result == 1) {
            model.addAttribute("message", "Thanh toán thành công!");
            payment.setAmount(totalPrice);
            payment.setPayment_method(PaymentMethod.CASH);
            payment.setStatus(StatusPayment.SUCCESS);
            payment.setPayment_time(LocalDateTime.now());
            paymentService.savePayment(payment);
            return "orderSuccess";  // Trang thông báo thành công
        } else if (result == 0) {
            model.addAttribute("message", "Thanh toán thất bại!");
            payment.setAmount(totalPrice);
            payment.setPayment_method(PaymentMethod.CASH);
            payment.setStatus(StatusPayment.FAILURE);
            payment.setPayment_time(LocalDateTime.now());
            paymentService.savePayment(payment);
            return "orderFail";  // Trang thông báo thất bại
        } else {
            model.addAttribute("message", "Có lỗi xảy ra trong quá trình thanh toán!");
            payment.setAmount(totalPrice);
            payment.setPayment_method(PaymentMethod.CASH);
            payment.setStatus(StatusPayment.FAILURE);
            payment.setPayment_time(LocalDateTime.now());
            paymentService.savePayment(payment);
            return "orderFail";  // Trang thông báo lỗi
        }
    }
}
