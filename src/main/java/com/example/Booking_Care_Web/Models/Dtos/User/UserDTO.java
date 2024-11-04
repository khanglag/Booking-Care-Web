package com.example.Booking_Care_Web.Models.Dtos.User;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String name;
    private String phone;
    private String email;
    private String gender;
    private String address;
    private String description;
    private String identificationCard;

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", identificationCard='" + identificationCard + '\'' +
                '}';
    }


}
