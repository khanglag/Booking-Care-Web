package com.example.Booking_Care_Web.Models.Dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckupPackpageDTO {

    private String package_id;

    private String name;

    private String description;

    private Double amount;

    @Override
    public String toString() {
        return "CheckupPackpageDTO{" +
                "packageid=' " + package_id + '\'' +
                ", name='"+ name + '\''+
                ", description='"+ description + '\'' +
                ", amount=' " + amount + '\'' +
                '}';
    }

}