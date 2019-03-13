package com.gameserver.gd.service;

import com.gameserver.gd.entity.User;
import com.gameserver.gd.entity.UserExample;
import com.gameserver.gd.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    public boolean IsRegistered(String username,String password){
        if (username == null || password == null)
            return false;
        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(username).andPasswordEqualTo(password);
        return userMapper.selectByExample(userExample).size()>0;
    }

    public boolean NewUserReg(String username,String password,String email){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        return userMapper.insertSelective(user)>0;
    }

    public boolean IsExist(String username){
        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(username);
        return userMapper.selectByExample(userExample).size()>0;
    }
}
