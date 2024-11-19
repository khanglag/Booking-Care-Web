package com.example.Booking_Care_Web.Services;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println(account.getPassword());
        System.out.println("================================");
        System.out.println(account);

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole().getName()) // Đảm bảo role có định dạng ROLE_*
                .build();
    }
}
