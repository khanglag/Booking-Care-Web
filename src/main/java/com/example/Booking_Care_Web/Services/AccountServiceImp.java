package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Repositories.AccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImp implements AccountService {

    private final PasswordEncoder passwordEncoder;

    public AccountServiceImp(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account findByUsername(String username) {
        Account account = accountRepository.findByUsername(username);
        return account;
    }


    @Transactional
    public Account createAccount(Account account) {
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    @Transactional
    public Account changePassword(String username, String newPassword) {
        Account accountExisting = accountRepository.findByUsername(username);
        accountExisting.setPassword(passwordEncoder.encode(newPassword));
        return accountRepository.save(accountExisting);
    }

    public Account findAccountById(String id) {
        Optional<Account> accountOptional = accountRepository.findById(id);
        return accountOptional.orElse(null);
    }
}
