package com.example.Booking_Care_Web.Models.Dtos;

import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Models.Entities.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private String accountId;
    private String userName;
    private String password;
    private String roleId;

    @Override
    public String toString() {
        return "Account{"
                + "accountId='" + accountId + '\''
                + ", userName='" + userName + '\''
                + ", password='" + password + '\''
                + ", roleId='" + roleId + '}';

    }
}
