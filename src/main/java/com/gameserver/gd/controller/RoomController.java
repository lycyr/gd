package com.gameserver.gd.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
@Api("联机房间内部操作方法实现")
public class RoomController {

    @RequestMapping(value = "/ready",method = {RequestMethod.POST,RequestMethod.GET})
    public int doReady(){
        return 0;
    }
}
