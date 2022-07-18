package com.t2009m1.springsecurity.service;

import com.t2009m1.springsecurity.SpringSecurityApplication;
import com.t2009m1.springsecurity.entity.dto.AccountRegisterDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSecurityApplication.class)
class AccountServiceTest {

    @Autowired
    AccountService accountService;
    @Test
    void register() {
        AccountRegisterDto accountRegisterDto = new AccountRegisterDto();
        accountRegisterDto.setUsername("tuananh");
        accountRegisterDto.setPassword("123456");
        accountRegisterDto.setRole(1);
        AccountRegisterDto afterCreate = accountService.register(accountRegisterDto);
        System.out.println(afterCreate);
    }
}