package com.example.Booking_Care_Web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AdminController {
    @GetMapping("/adminPage")
    public String showAdminPage() {
        return "admin/adminPage"; // Đảm bảo rằng đường dẫn là "admin/adminPage"
    }
}
