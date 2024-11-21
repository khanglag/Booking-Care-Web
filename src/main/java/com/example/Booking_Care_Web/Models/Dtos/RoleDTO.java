package com.example.Booking_Care_Web.Models.Dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleDTO {
    private String roleId;
    private String name;

    @Override
    public String toString(){
        return "RoleDTO{" +
                "roleId='" + roleId +'\''+
                ",name='" + name +'\''+
                '}';
    }
}