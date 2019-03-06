package com.gameserver.gd.dao;

import com.gameserver.gd.entity.User;
import com.gameserver.gd.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private UserMapper userMapper;


}
