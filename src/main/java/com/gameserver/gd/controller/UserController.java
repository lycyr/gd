package com.gameserver.gd.controller;

import com.gameserver.gd.Utils.Security;
import com.gameserver.gd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
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

    @RequestMapping(value = "doreg",method = {RequestMethod.POST,RequestMethod.GET})
    public boolean DoReg(String username,String password,String email){
        if (username == null || password==null || email==null)
            return false;
        return userService.NewUserReg(username,password,email);
    }

    @RequestMapping(value = "/check",method = {RequestMethod.POST,RequestMethod.GET})
    public boolean IsRegistered(String username,String password){
        return userService.IsRegistered(username,password);
    }

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String Login(String username,String password){
        if (userService.IsRegistered(username,password)){
            String encodeStr = Security.md5(username+password);
            System.out.println(encodeStr);
            return encodeStr;
        }
        else
            return "failed";
    }
}
