package com.example.Booking_Care_Web.Models.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @Size(max = 7)
    @Column(name = "account_id", nullable = false, length = 7)
    private String accountId;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id",referencedColumnName = "user_id", nullable = false)
    private User user;

    @NotNull
    @Lob
    @Column(name = "username", nullable = false)
    private String username;

    @Size(max = 100)
    @NotNull
    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;


    @Override
    public String toString() {
        return "Account{"
                + "accountId='" + accountId + '\''
                + ", userName='" + username + '\''
                + ", password='" + password + '\''
                + ", roleId='" + role.getRoleId() + '}';

    }

}