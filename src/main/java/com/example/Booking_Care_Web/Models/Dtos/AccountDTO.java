package com.example.Booking_Care_Web.Models.Dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private String accountId;
    private User user;
    private String username;
    private String password;
    private Role role;

    @Override
    public String toString() {
        return "AccountDTO{" +
                "accountId='" + accountId +'\''+
                ", user='" + user +'\''+
                ", username='"+ username+'\''+
                ", password= '"+ password+'\''+
                ",role='"+ role+ '\''+
                '}'
    }
}