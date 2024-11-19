package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImp implements AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Override
    public Account findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return account;
    }
}
