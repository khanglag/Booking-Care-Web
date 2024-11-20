package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Models.Entities.Role;
import com.example.Booking_Care_Web.Models.Entities.User;
import com.example.Booking_Care_Web.Services.AccountServiceImp;
import com.example.Booking_Care_Web.Services.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringBootTest
@SpringJUnitWebConfig
public class AccounrServiceTest {
    @Autowired
    private AccountServiceImp accountServiceImp;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Test
    public void test() {
        Account account = accountServiceImp.findByUsername("Admin");
        System.out.println(account);
    }

    @Test
    public void saveAccount() {
        User user = new User();
        user.setUserId("000281");

        Role role = new Role();
        role.setRoleId("patient");

        Account account = new Account();
        account.setAccountId("000281");
        account.setRole(role);
        account.setUsername("MK");
        account.setPassword("1234");
        accountServiceImp.createAccount(account);
    }

    @Test
    public void findByUsername() {

        System.out.println(accountServiceImp.findByUsername("Admin"));
    }
}
