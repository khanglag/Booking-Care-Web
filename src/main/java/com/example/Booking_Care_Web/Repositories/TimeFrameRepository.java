package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.TimeFrame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.Optional;

@Repository
public interface TimeFrameRepository extends JpaRepository<TimeFrame, String> {
    @Query("SELECT tf FROM TimeFrame tf WHERE tf.timeId = :timeId")
    Optional<TimeFrame> findById(@Param("timeId") String timeId);

    @Modifying
    @Query(value ="INSERT INTO timeframe (timeId, timeStart, timeEnd) VALUES (:timeId, :timeStart, :timeEnd, :appointments)", nativeQuery = true)
    void insertTimeFrame(String timeId, LocalTime timeStart, LocalTime timeEnd );
}