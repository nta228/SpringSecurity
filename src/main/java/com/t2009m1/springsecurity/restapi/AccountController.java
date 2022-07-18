package com.t2009m1.springsecurity.restapi;

import com.t2009m1.springsecurity.entity.dto.AccountLoginDto;
import com.t2009m1.springsecurity.entity.dto.AccountRegisterDto;
import com.t2009m1.springsecurity.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    final AccountService accountService;
    @RequestMapping(path = "register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AccountRegisterDto accountRegisterDto){
        return ResponseEntity.ok().body(accountService.register(accountRegisterDto));
    }
    @RequestMapping(path = "login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AccountLoginDto accountLoginDto){
        return ResponseEntity.ok().body(accountService.login(accountLoginDto));
    }
    @RequestMapping(method = RequestMethod.GET)
    public  String getInformation(){

        return "";
    }

}
