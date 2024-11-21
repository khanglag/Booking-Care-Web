package com.example.Booking_Care_Web.Models.Dtos;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeFrameDTO {
    private String timeId;
    private LocalTime timeStart;
    private LocalTime timeEnd;

    @Override
    public String toString(){
        return "TimeFrameDTO{" +
                "timeId='" + timeId +'\'' +
                ",timeStart='" + timeStart +'\'' +
                ",timeEnd='" + timeEnd +'\''+
                '}';
    }
}