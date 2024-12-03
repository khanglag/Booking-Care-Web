package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Services.AccountService;
import com.example.Booking_Care_Web.Services.AppointmentServiceImpl;
import com.example.Booking_Care_Web.Services.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentServiceImpl appointmentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private UserServiceImpl userServiceImpl;
//    @GetMapping("/api/weekly-appointments-chart")
//    public List<Map<String, Object>> getWeeklyAppointmentsChart() {
//        return appointmentService.getAppointmentsByWeek();
//    }

    @GetMapping("/weekly-appointments-chart")
    public String goWeekly(Model model) {
        List<Map<String, Object>> weeklyAppointments = appointmentService.getAppointmentsByWeek();

        // Chuyển dữ liệu vào model để render trong view
        model.addAttribute("weeklyAppointments", weeklyAppointments);
        System.out.println(weeklyAppointments);
        // Trả về tên view (HTML hoặc Thymeleaf template)
        return "weekly-appointments";
    }
    @GetMapping("/chart")
    public void generateChart(HttpServletResponse response) throws IOException {
        List<Map<String, Object>> weeklyAppointments = appointmentService.getAppointmentsByWeek();

        // Tạo dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Map<String, Object> appointment : weeklyAppointments) {
            dataset.addValue(
                    ((Number)appointment.get("appointmentCount")).doubleValue(),
                    "Số Cuộc Hẹn",
                    "Tuần " + appointment.get("week")
            );
        }

        // Tạo biểu đồ
        JFreeChart chart = ChartFactory.createBarChart(
                "Số Cuộc Hẹn Theo Tuần",  // Tiêu đề
                "Tuần",                   // Nhãn trục X
                "Số Lượng",               // Nhãn trục Y
                dataset,                  // Dataset
                PlotOrientation.VERTICAL, // Hướng biểu đồ
                true, true, false         // Các tùy chọn legend, tooltips, URLs
        );

        // Thiết lập màu sắc
        chart.getCategoryPlot().getRenderer().setSeriesPaint(0, Color.BLUE);

        // Xuất biểu đồ
        response.setContentType("image/png");
        ChartUtils.writeChartAsPNG(response.getOutputStream(), chart, 600, 400);
    }

//    @GetMapping("/api/appointments-by-date")
//    public List<Map<String, Object>> getAppointmentsByDateRange(
//            @RequestParam("startDate") String startDate,
//            @RequestParam("endDate") String endDate) {
//        return appointmentService.getAppointmentsByDateRange(startDate, endDate);
//    }

//    @GetMapping("/appointments-by-year")
//    public List<Map<String, Object>> getAppointmentsByYear(@PathVariable String year) {
//        return appointmentService.getAppointmentsByYear(year);
//    }

//    @GetMapping("/statisticManagement")
//    public String generateChartAppointmentByYear(ModelMap modelMap, @PathVariable String year, Authentication authentication, HttpSession session,Model model) {
//        authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof UserDetails) {
//            User user = (User) principal;
//            String username = user.getUsername();
//            Account account = accountService.findByUsername(username);
//            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
//            session.setAttribute("user", user_normal);
//            session.setAttribute("userID", user_normal.getUserId());
//            session.setAttribute("authentication", authentication);
//            model.addAttribute("user", user_normal);
//        }
//        List<Map<String, Object>> canvajsDataList = appointmentService.getAppointmentsByYear(year);
//        System.out.println("Data sent to view: " + canvajsDataList);
//        modelMap.addAttribute("canvajsDataList", canvajsDataList);
//        modelMap.addAttribute("year", year);
//        return "admin/statisticManagement";
//    }
    @GetMapping("/statisticManagement")
    public String generateChartAppointmentByYear(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String startDate,
            @RequestParam(required = false) String endDate,
            ModelMap modelMap,
            Authentication authentication,
            HttpSession session,
            Model model) {
        authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            User user = (User) principal;
            String username = user.getUsername();
            Account account = accountService.findByUsername(username);
            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
            session.setAttribute("user", user_normal);
            session.setAttribute("userID", user_normal.getUserId());
            session.setAttribute("authentication", authentication);
            model.addAttribute("user", user_normal);
        }
        if (year == null || year.isEmpty()) {
            year = "2024";
        }
        List<Map<String, Object>> canvajsDataList = appointmentService.getAppointmentsByYear(year);
        modelMap.addAttribute("canvajsDataList", canvajsDataList);
        modelMap.addAttribute("year", year);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedStartDate = null;
        String formattedEndDate = null;
        if(startDate == null || startDate.isEmpty() || endDate == null || endDate.isEmpty()) {
            LocalDate now = LocalDate.now();
            LocalDate firstDayOfMonth = now.withDayOfMonth(1); // Ngày đầu tháng
            LocalDate lastDayOfMonth = now.withDayOfMonth(now.lengthOfMonth()); // Ngày cuối tháng
            startDate = firstDayOfMonth.format(outputFormatter); // Chuyển sang định dạng yyyy-MM-dd
            endDate = lastDayOfMonth.format(outputFormatter);
            List<Map<String, Object>> appointmentsByDate = appointmentService.getAppointmentsByDateRange(startDate, endDate);
            modelMap.addAttribute("appointmentsByDate", appointmentsByDate);
            modelMap.addAttribute("startDate", startDate);
            modelMap.addAttribute("endDate", endDate);
            return "admin/statisticManagement";
        }

        try {
            if (startDate != null && !startDate.isEmpty()) {
                LocalDate start = LocalDate.parse(startDate.trim(), inputFormatter);
                formattedStartDate = start.format(outputFormatter);
            }
            if (endDate != null && !endDate.isEmpty()) {
                LocalDate end = LocalDate.parse(endDate.trim(), inputFormatter);
                formattedEndDate = end.format(outputFormatter);
            }
            if (formattedStartDate != null && formattedEndDate != null) {
                System.out.println("==============Sau khi chuyển đổi ===============" + formattedStartDate+formattedEndDate);
                List<Map<String, Object>> appointmentsByDate = appointmentService.getAppointmentsByDateRange(formattedStartDate, formattedEndDate);
                modelMap.addAttribute("appointmentsByDate", appointmentsByDate);
                System.out.println("=============================" + appointmentsByDate);
            }

        } catch (DateTimeParseException e) {
            modelMap.addAttribute("errorMessage", "Định dạng ngày tháng không hợp lệ. Vui lòng nhập theo định dạng yyyy-MM-dd.");
            return "admin/statisticManagement";
        }
        modelMap.addAttribute("startDate", formattedStartDate);
        modelMap.addAttribute("endDate", formattedEndDate);
        return "admin/statisticManagement";
    }


    // Phương thức để lấy số cuộc hẹn theo ngày trong khoảng thời gian
//    @RequestMapping("/appointmentsByDateRange")
//    public String getAppointmentsByDateRange(ModelMap modelMap, @RequestParam String startDate, @RequestParam String endDate) {
//        // Gọi service để lấy dữ liệu cuộc hẹn theo khoảng thời gian
//        List<Map<String, Object>> appointmentsByDate = appointmentService.getAppointmentsByDateRange(startDate, endDate);
//
//        // Gửi dữ liệu sang view
//        modelMap.addAttribute("appointmentsByDate", appointmentsByDate);
//        modelMap.addAttribute("startDate", startDate);
//        modelMap.addAttribute("endDate", endDate);
//
//        return "appointmentsByDate"; // Tên view JSP hoặc Thymeleaf
//    }
//    @RequestMapping("/statisticManagement/{year}")
//    public String generateChartAppointmentByYear(
//            ModelMap modelMap,
//            @PathVariable String year,
//            Authentication authentication,
//            HttpSession session,
//            Model model,
//            @RequestParam(required = false) String startDate,
//            @RequestParam(required = false) String endDate) {
//
//        // Xử lý thông tin người dùng (giữ nguyên như bạn đã viết)
//        authentication = SecurityContextHolder.getContext().getAuthentication();
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof UserDetails) {
//            User user = (User) principal;
//            String username = user.getUsername();
//            Account account = accountService.findByUsername(username);
//            com.example.Booking_Care_Web.Models.Entities.User user_normal = userServiceImpl.findById(account.getAccountId());
//            session.setAttribute("user", user_normal);
//            session.setAttribute("userID", user_normal.getUserId());
//            session.setAttribute("authentication", authentication);
//            model.addAttribute("user", user_normal);
//        }
//
//        // Dữ liệu biểu đồ theo năm
//        List<Map<String, Object>> canvajsDataList = appointmentService.getAppointmentsByYear(year);
//        modelMap.addAttribute("canvajsDataList", canvajsDataList);
//        modelMap.addAttribute("year", year);
//
//        // Xử lý dữ liệu biểu đồ theo ngày (nếu có startDate và endDate)
//        if (startDate != null && endDate != null) {
//            List<Map<String, Object>> appointmentsByDate = appointmentService.getAppointmentsByDateRange(startDate, endDate);
//            modelMap.addAttribute("appointmentsByDate", appointmentsByDate);
//            modelMap.addAttribute("startDate", startDate);
//            modelMap.addAttribute("endDate", endDate);
//        } else {
//            // Thiết lập giá trị mặc định nếu không có ngày
//            modelMap.addAttribute("startDate", "2024-01-01");
//            modelMap.addAttribute("endDate", "2024-12-31");
//            modelMap.addAttribute("appointmentsByDate", new ArrayList<>()); // Dữ liệu trống
//        }
//
//        return "admin/statisticManagement";
//    }




//    @RequestMapping("/appointmentByYear/{year}")
//    public String abc(ModelMap modelMap, @PathVariable String year) {
//        List<Map<String, Object>> canvajsDataList = appointmentService.getAppointmentsByYear(year);
//        System.out.println("Data sent to view: " + canvajsDataList);
//        modelMap.addAttribute("canvajsDataList", canvajsDataList);
//        modelMap.addAttribute("year", year);
//        return "admin/statisticManagement"; // Đổi trả về file statisticManagement
//    }
}
