package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.AppointmentDTO;
import com.example.Booking_Care_Web.Models.Entities.Appointment;
import com.example.Booking_Care_Web.Repositories.AppointmentRepository;
import com.example.Booking_Care_Web.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll().stream()
                .map(appointment -> new AppointmentDTO(
                        appointment.getAppointmentId(),
                        appointment.getPatient().getUserId(),
                        appointment.getDoctor().getUserId(),
                        appointment.getAvailableDatetime(),
                        appointment.getPackageId(),
                        appointment.getExaminationDay(),
                        appointment.getTimeId(),
                        appointment.getNote(),
                        appointment.getStatus()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public Appointment save(Appointment appointment) {
       return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment findAppointmentById(int id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        return appointment;
    }

    @Override
    public List<Appointment> findAppointmentByPatientId(String id) {
        return appointmentRepository.findByPatientId(id);
    }

    @Override
    public List<Appointment> findAppointmentByDoctorId(String id) {
        return appointmentRepository.findByDoctorId(id);
    }

    @Override
    public List<Appointment> findApppontmentByStatus(String status) {
        return appointmentRepository.findByStatus(status);
    }

    @Transactional
    public Appointment updateAppointment(Appointment appointment) {
        Appointment appointment1 = appointmentRepository.findById(appointment.getAppointmentId()).orElse(null);
        if (appointment1 == null) {
            throw new RuntimeException("Appointment not found with ID: " + appointment.getAppointmentId());
        }
        appointment1.setPatient(appointment.getPatient());
        appointment1.setDoctor(appointment.getDoctor());
        appointment1.setAvailableDatetime(appointment.getAvailableDatetime());
        appointment1.setPackageField(appointment.getPackageField());
        appointment1.setExaminationDay(appointment.getExaminationDay());
        appointment1.setTime(appointment.getTime());
        appointment1.setNote(appointment.getNote());
        appointment1.setStatus(appointment.getStatus());
        return appointmentRepository.save(appointment1);
    }

    public List<Map<String, Object>> getAppointmentsByWeek() {
        List<Object[]> results = appointmentRepository.countAppointmentsByWeek();
        List<Map<String, Object>> weeklyAppointments = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> weekData = new HashMap<>();
            weekData.put("week", result[0]);
            weekData.put("appointmentCount", result[1]);
            weeklyAppointments.add(weekData);
        }
        return weeklyAppointments;
    }

//    // Phương thức lấy cuộc hẹn theo ngày trong khoảng thời gian
//    public List<Map<String, Object>> getAppointmentsByDateRange(String startDateStr, String endDateStr) {
//        // Gọi phương thức trong Repository để lấy dữ liệu với tham số String
//        List<Object[]> results = appointmentRepository.countAppointmentsByDateRange(startDateStr, endDateStr);
//
//        List<Map<String, Object>> appointmentsByDate = new ArrayList<>();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//
//        // Chuyển đổi kết quả query thành danh sách các map
//        for (Object[] result : results) {
//            Map<String, Object> dateData = new HashMap<>();
//            dateData.put("date", sdf.format((Date) result[0])); // Lấy ngày
//            dateData.put("appointmentCount", result[1]); // Lấy số cuộc hẹn
//            appointmentsByDate.add(dateData);
//        }
//
//        return appointmentsByDate;
//    }

    public List<Map<String, Object>> getAppointmentsByYear(String year) {

        List<Object[]> results = appointmentRepository.countAppointmentsByMonth(year);

        List<Map<String, Object>> appointmentsByMonth = new ArrayList<>();
        int[] appointmentCounts = new int[12];

        // Chuyển đổi kết quả query thành dạng danh sách các map
        for (Object[] result : results) {
            Integer month = (Integer) result[0];  // Tháng
            Long appointmentCount = (Long) result[1];  // Số cuộc hẹn

            // Cập nhật số cuộc hẹn cho tháng tương ứng (vì tháng được trả về là 1-12, nên phải trừ 1 để làm chỉ số mảng)
            appointmentCounts[month - 1] = appointmentCount.intValue();
        }

        // Đảm bảo rằng mỗi tháng có dữ liệu, nếu không có cuộc hẹn thì là 0
        for (int i = 0; i < 12; i++) {
            Map<String, Object> monthData = new HashMap<>();
            monthData.put("month", i + 1);  // Lấy tháng từ 1 đến 12
            monthData.put("appointmentCount", appointmentCounts[i]);  // Số cuộc hẹn trong tháng, nếu không có thì là 0
            appointmentsByMonth.add(monthData);
        }

        return appointmentsByMonth;
    }

    public List<Map<String, Object>> getAppointmentsByDateRange(String startDateStr, String endDateStr) {
        // Chuyển đổi startDateStr và endDateStr thành LocalDate
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate startDate = LocalDate.parse(startDateStr, formatter);
        LocalDate endDate = LocalDate.parse(endDateStr, formatter);
        System.out.println("==đã chuyển thành localdate====="+startDate+"======"+ endDate);
        // Lấy kết quả từ repository
        List<Object[]> results = appointmentRepository.countAppointmentsByDateRange(startDateStr, endDateStr);

        // Tạo danh sách các ngày trong khoảng thời gian
        List<Map<String, Object>> appointmentsByDate = new ArrayList<>();
        Map<LocalDate, Long> appointmentDataMap = new HashMap<>();

        for (Object[] result : results) {
            // Kiểm tra kiểu dữ liệu của result[0]
            if (result[0] instanceof java.sql.Timestamp) {
                LocalDate date = ((java.sql.Timestamp) result[0]).toLocalDateTime().toLocalDate(); // Convert Timestamp to LocalDate
                Long appointmentCount = (Long) result[1];
                appointmentDataMap.put(date, appointmentCount);
            } else if (result[0] instanceof java.sql.Date) {
                LocalDate date = ((java.sql.Date) result[0]).toLocalDate(); // Convert Date to LocalDate
                Long appointmentCount = (Long) result[1];
                appointmentDataMap.put(date, appointmentCount);
            } else {
                throw new IllegalArgumentException("Unsupported date type: " + result[0].getClass());
            }
        }



        // Duyệt qua tất cả các ngày trong khoảng thời gian và đảm bảo mỗi ngày có dữ liệu
        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            Map<String, Object> dateData = new HashMap<>();
            dateData.put("date", date.format(formatter)); // Format date as dd/MM/yyyy
            dateData.put("appointmentCount", appointmentDataMap.getOrDefault(date, 0L)); // Nếu không có dữ liệu thì gán 0
            appointmentsByDate.add(dateData);
        }

        return appointmentsByDate;
    }
}
