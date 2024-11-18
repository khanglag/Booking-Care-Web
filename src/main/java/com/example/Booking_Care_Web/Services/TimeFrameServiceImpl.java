package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.TimeFrameDTO;
import com.example.Booking_Care_Web.Models.Entities.TimeFrame;
import com.example.Booking_Care_Web.Repositories.TimeFrameRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeFrameServiceImpl implements TimeFrameService{

    @Autowired
    private TimeFrameRepository timeFrameRepository;

    @Override
    public TimeFrame findById(String timeId) {return timeFrameRepository.findById(timeId).get();}

    @Override
    public List<TimeFrameDTO> findAll() {
        return timeFrameRepository.findAll().stream()
                .map(tf -> new TimeFrameDTO(
                       tf.getTimeId(),
                       tf.getTimeStart(),
                       tf.getTimeEnd()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public TimeFrame saveTimeFrame(TimeFrame tf){
        timeFrameRepository.insertTimeFrame(tf.getTimeId, tf.getTimeStart, tf.getTimeEnd);
    return tf;
    }
}