package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole().getName()) // Đảm bảo role có định dạng ROLE_*
                .build();
    }
}
