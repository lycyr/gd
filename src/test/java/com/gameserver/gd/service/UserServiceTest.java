package com.gameserver.gd.service;

import com.gameserver.gd.GdApplicationTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
//使用springboot进行测试需要继承主类GdApplicationTests
public class UserServiceTest extends GdApplicationTests {

    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @Autowired
    private UserService userService;

    @Test
    public void isRegistered() {
       boolean is = userService.IsRegistered("lycyr","19980814");
        logger.info("true");
    }
}