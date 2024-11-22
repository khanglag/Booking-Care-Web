package com.example.Booking_Care_Web.Models.Dtos;

import lombok.*;

import java.time.LocalTime;

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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CheckupPackpageDTO {

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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class RoleDTO {
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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class TimeFrameDTO {
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
}
