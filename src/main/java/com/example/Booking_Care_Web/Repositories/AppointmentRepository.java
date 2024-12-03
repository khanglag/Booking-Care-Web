package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("select d from Appointment d where d.doctor.userId = :id")
    List<Appointment> findByDoctorId(String id);

    @Query("select d from Appointment d where d.patient.userId = :id")
    List<Appointment> findByPatientId(String id);

    @Query("SELECT a from  Appointment a WHERE a.status = :status")
    List<Appointment> findByStatus(String status);

    @Query(value = "SELECT WEEK(examination_day) as week, COUNT(*) as appointmentCount " +
            "FROM appointment " +
            "WHERE status = 'ĐÃ DUYỆT' " +
            "GROUP BY WEEK(examination_day) " +
            "ORDER BY week", nativeQuery = true)
    List<Object[]> countAppointmentsByWeek();

    // Query lọc các cuộc hẹn trong khoảng ngày chỉ định với tham số String
    @Query(value = "SELECT examination_day as date, COUNT(*) as appointmentCount " +
            "FROM appointment " +
            "WHERE examination_day BETWEEN STR_TO_DATE(:startDate, '%d/%m/%Y') " +
            "AND STR_TO_DATE(:endDate, '%d/%m/%Y') " +
            "GROUP BY examination_day " +
            "ORDER BY date", nativeQuery = true)
    List<Object[]> countAppointmentsByDateRange(@Param("startDate") String startDate,
                                                @Param("endDate") String endDate);

    @Query(value = "SELECT MONTH(examination_day) as month, COUNT(*) as appointmentCount " +
            "FROM appointment " +
            "WHERE status = 'ĐÃ DUYỆT' " +
            "AND YEAR(examination_day) = :year " +
            "GROUP BY MONTH(examination_day) " +
            "ORDER BY month", nativeQuery = true)
    List<Object[]> countAppointmentsByMonth(@Param("year") String year);
}
