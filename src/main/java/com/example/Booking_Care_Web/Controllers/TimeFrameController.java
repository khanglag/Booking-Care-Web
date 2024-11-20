package com.example.Booking_Care_Web.Controllers;

import com.example.Booking_Care_Web.Models.Dtos.TimeFrameDTO;
import com.example.Booking_Care_Web.Models.Entities.TimeFrame;
import com.example.Booking_Care_Web.Services.TimeFrameServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class TimeFrameController {

    @Autowired
    private TimeFrameServiceImpl timeFrameServiceImpl;

    @GetMapping("/timeframes")
    public List<TimeFrameDTO> findAll() {return timeFrameServiceImpl.findAll(); }

    @GetMapping("/timeId")
    public ResponseEntity<TimeFrameDTO> getTFById(@RequestParam String timeId) {
        try{
            TimeFrame tf = timeFrameServiceImpl.findById(timeId);
            TimeFrameDTO timeFrameDTO = new TimeFrameDTO(
                    tf.getTimeId(),
                    tf.getTimeStart(),
                    tf.getTimeEnd()
            );
            return ResponseEntity.ok(TimeFrameDTO);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/create")
    public ResponseEntity<TimeFrame> createTimeFrame(@RequestBody TimeFrame timeFrame){
        TimeFrame savedTimeFrame = timeFrameServiceImpl.saveTimeFrame(timeFrame);
        return ResponseEntity.status(HttpStatus,CREATED).body(savedTimeFrame);
    }
}