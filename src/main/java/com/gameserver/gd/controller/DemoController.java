package com.gameserver.gd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class DemoController {
    @RequestMapping("/getUser")
    public String index(){
        return "hello world!";
    }
}
