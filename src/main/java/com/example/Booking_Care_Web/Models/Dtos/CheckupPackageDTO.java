package com.example.Booking_Care_Web.Models.Dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckupPackageDTO {

    private String id;

    private String name;

    private String description;

    private Double amount;

    @Overide
    public String toString() {
        return "CheckupPackpageDTO{" +
                "id=' " + id + '\'' +
                ", name='"+ name + '\''+
                ", description='"+ description + '\'' +
                ", amount=' " + amount + '\'' +
                '}';
    }

}