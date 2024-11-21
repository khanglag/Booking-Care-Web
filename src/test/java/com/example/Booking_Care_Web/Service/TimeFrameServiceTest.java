package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Entities.TimeFrame;
import com.example.Booking_Care_Web.Repositories.TimeFrameRepository;
import com.example.Booking_Care_Web.Services.TimeFrameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalTime;

public class TimeFrameServiceTest {

    @Mock
    private TimeFrameRepository timeFrameRepository;

    @InjectMocks
    private TimeFrameServiceImpl timeFrameServiceImp;

    @BeforeEach
    public void setUp() { MockitoAnnotations.openMocks(this);}

    @Test
    public void testCreateTF(){

        TimeFrame timeFrameDTO = new TimeFrame();
        timeFrameDTO.setTimeId("0000009");
        timeFrameDTO.setTimeStart(LocalTime.parse("08:00:00"));
        timeFrameDTO.setTimeEnd(LocalTime.parse("11:30:00"));

        System.out.println(timeFrameDTO);
        TimeFrame savedTimeFrame = timeFrameServiceImp.saveTimeFrame(timeFrameDTO);
        System.out.println(savedTimeFrame);


    }

    @Test
    public void testFindById(){
//        String id = "0000001";
//        Optional<TimeFrameDTO> timeFrameDTOOptional = timeFrameService.findById(id);
//        System.out.println(timeFrameDTOOptional.toString());
    }
}