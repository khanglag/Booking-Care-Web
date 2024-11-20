package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
}
