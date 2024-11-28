package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Services.AppointmentServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentServiceImpl appointmentService;

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

    @RequestMapping("/appointmentByYear/{year}")
    public String generateChartAppointmentByYear(ModelMap modelMap, @PathVariable String year) {
        List<Map<String, Object>> canvajsDataList = appointmentService.getAppointmentsByYear(year);
        System.out.println("Data sent to view: " + canvajsDataList);
        modelMap.addAttribute("canvajsDataList", canvajsDataList);
        modelMap.addAttribute("year", year);
        return "monthlyAppointments";
    }

    // Phương thức để lấy số cuộc hẹn theo ngày trong khoảng thời gian
    @RequestMapping("/appointmentsByDateRange")
    public String getAppointmentsByDateRange(ModelMap modelMap, @RequestParam String startDate, @RequestParam String endDate) {
        // Gọi service để lấy dữ liệu cuộc hẹn theo khoảng thời gian
        List<Map<String, Object>> appointmentsByDate = appointmentService.getAppointmentsByDateRange(startDate, endDate);

        // Gửi dữ liệu sang view
        modelMap.addAttribute("appointmentsByDate", appointmentsByDate);
        modelMap.addAttribute("startDate", startDate);
        modelMap.addAttribute("endDate", endDate);

        return "appointmentsByDate"; // Tên view JSP hoặc Thymeleaf
    }

}
