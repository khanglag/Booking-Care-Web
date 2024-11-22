package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Dtos.TimeFrameDTO;
import com.example.Booking_Care_Web.Models.Entities.Role;
import com.example.Booking_Care_Web.Models.Entities.TimeFrame;
import com.example.Booking_Care_Web.Repositories.TimeFrameRepository;
import jakarta.persistence.EntityNotFoundException;
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
    public TimeFrame findById(String timeId) {
        return timeFrameRepository.findById(timeId)
                .orElseThrow(() -> new EntityNotFoundException("cp không tìm thấy với ID: " + timeId));
    }

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
//    public TimeFrame saveTimeFrame(TimeFrame tf){
//        timeFrameRepository.insertTimeFrame(tf.getTimeId(), tf.getTimeStart(), tf.getTimeEnd());
//    return tf;
//    }

    public TimeFrame saveTimeFrame(TimeFrame tf){
        return timeFrameRepository.save(tf);
    }

    public TimeFrame updateTF(String id, TimeFrame updateTF){
        TimeFrame timeFrameExisting = timeFrameRepository.findById(id).orElseThrow(() -> new RuntimeException("ko ton tai"+id));

        if (updateTF.getTimeStart() != null){
            timeFrameExisting.setTimeStart(updateTF.getTimeStart());
        }
        if (updateTF.getTimeEnd() != null){
            timeFrameExisting.setTimeEnd(updateTF.getTimeEnd());
        }
        return timeFrameRepository.save(timeFrameExisting);
    }
}