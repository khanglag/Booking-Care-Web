package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Entities.TimeFrame;
import com.example.Booking_Care_Web.Repositories.TimeFrameRepository;
import com.example.Booking_Care_Web.Services.TimeFrameServiceImpl;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalTime;

@SpringBootTest
@SpringJUnitWebConfig
public class TimeFrameServiceTest {

    @Autowired
    private TimeFrameServiceImpl timeFrameServiceImp;

    @Test
    public void testCreateTimeFrame(){

        TimeFrame timeFrameDTO = new TimeFrame();
        timeFrameDTO.setTimeId("0213928");
        timeFrameDTO.setTimeStart(LocalTime.parse("08:00:00"));
        timeFrameDTO.setTimeEnd(LocalTime.parse("11:30:00"));

        System.out.println(timeFrameDTO);
        TimeFrame savedTF = timeFrameServiceImp.saveTimeFrame(timeFrameDTO);
        System.out.println(savedTF);
    }

    @Test
    public void testFindById() {

        String id = "0000001";
        TimeFrame timeFrame = timeFrameServiceImp.findById(id);
        System.out.println(timeFrame.toString());
    }

    @Test
    public void testUpdateTF() {
        TimeFrame timeFrameDTO = new TimeFrame();
        timeFrameDTO.setTimeId("0000001");
        timeFrameDTO.setTimeStart(LocalTime.parse("08:00:00"));
        timeFrameDTO.setTimeEnd(LocalTime.parse("11:40:00"));

        TimeFrame update = timeFrameServiceImp.updateTF("0000001", timeFrameDTO);
    }
}
