package com.example.Booking_Care_Web.Service;

import com.example.Booking_Care_Web.Models.Entities.Account;
import com.example.Booking_Care_Web.Services.AccountServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

@SpringBootTest
@SpringJUnitWebConfig
public class AccounrServiceTest {
    @Autowired
    private AccountServiceImp accountServiceImp;
    @Test
    public void test() {
        Account account = accountServiceImp.findByUsername("Admin");
        System.out.println(account);
    }
}
