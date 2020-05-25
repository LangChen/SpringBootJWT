package com.chlang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/test")
    public Map<String,Object> test(){
        Map<String,Object> result = new HashMap<>();
        result.put("code",0);
        result.put("data","test");

        return result;
    }

}
