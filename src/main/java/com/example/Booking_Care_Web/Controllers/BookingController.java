package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.CheckupPackpageDTO;
import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Models.Entities.CheckupPackpage;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Services.AccountService;
import com.example.Booking_Care_Web.Services.CheckupPackpageServiceImpl;
import com.example.Booking_Care_Web.Services.UserService;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookingController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Autowired
    private CheckupPackpageServiceImpl checkupPackpageServiceImpl;

    @RequestMapping("/booking")
    public String booking() {
        return "booking";
    }
    @GetMapping("/booking/{id}")
    public String bookingPage(@PathVariable("id") String doctorId,
                              Model model,
                              HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username != null) {
            model.addAttribute("user", username);
        } else {
            return "signin";
        }
        Account account = accountService.findByUsername(username);
        com.example.Booking_Care_Web.Models.Entities.User user = account.getUser();
        model.addAttribute("nameOfUser",user.getName());

        User doctor = userServiceImpl.findById(doctorId);
        model.addAttribute("doctor",doctor);

        List<CheckupPackpageDTO> checkupPackpages = checkupPackpageServiceImpl.findAll();
        model.addAttribute("checkupPackages", checkupPackpages);
        return "booking";
    }

}
