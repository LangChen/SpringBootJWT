package com.chlang.controller;

import com.chlang.common.helper.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping("/login")
    public Map<String,Object> login(String userAccount, String password){
        String token = "";
        if("test".equals(userAccount) && "111111".equals(password)){
            token = jwtHelper.createToken(userAccount);
        }

        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("data",token);
        return result;
    }

}
