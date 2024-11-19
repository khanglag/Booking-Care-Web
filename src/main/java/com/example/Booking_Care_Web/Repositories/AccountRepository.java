package com.example.Booking_Care_Web.Repositories;

import com.example.Booking_Care_Web.Models.Entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
    Account findByUsername(String username);
}
