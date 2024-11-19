package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Entities.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account findByUsername(String username);
}
