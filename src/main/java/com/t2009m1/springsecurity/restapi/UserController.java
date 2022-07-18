package com.t2009m1.springsecurity.restapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @RequestMapping(method = RequestMethod.GET)
    public String say(){
        return "Hello User";
    }
}
