package com.gameserver.gd.controller;

import com.gameserver.gd.entity.User;
import com.gameserver.gd.mapper.UserMapper;
import com.gameserver.gd.service.UserService;
import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value = "doreg",method = RequestMethod.POST)
    public boolean DoReg(String username,String password,String email){
        if (username == null || password==null || email==null)
            return false;
        return userService.NewUserReg(username,password,email);
    }

    @RequestMapping(value = "/check",method = RequestMethod.POST)
    public boolean IsRegistered(String username,String password){
        return userService.IsRegistered(username,password);
    }
}
