package com.gameserver.gd.controller;

import com.gameserver.gd.pvp.Hall;
import com.gameserver.gd.service.RoomService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/room")
@Api("联机房间内部操作方法实现")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService){
        this.roomService = roomService;
    }

    //玩家进行准备时会调用此方法
    @RequestMapping(value = "/ready",method = {RequestMethod.POST,RequestMethod.GET})
    public boolean doReady(int roomindex){
        if (roomindex <0 || roomindex>=36)
            return false;
        if (Hall.getRooms().get(roomindex).getReady() == 2)
            return true;
        System.out.println("房间"+roomindex+"内有人进行了准备操作");
        return roomService.ready(Hall.getRooms().get(roomindex));
    }
    //先准备的玩家将会调用此方法，进行查询另一名玩家是否已经准备（心跳机制）
    @RequestMapping(value = "/getready",method = {RequestMethod.POST})
    public boolean getReady(int roomindex){
        if (roomindex <0 || roomindex>=36)
            return false;
        else if (Hall.getRooms().get(roomindex).getReady() == 2)
            return true;
        else
            return false;
    }

    @RequestMapping(value = "/cancel",method = {RequestMethod.POST,RequestMethod.GET})
    public void cannelReady(int roomindex){
        if (roomindex <0 || roomindex>=36)
            throw new IllegalArgumentException("error！错误原因：这是一个无效的房间号，请重新登录");
        try {
            roomService.cannel(Hall.getRooms().get(roomindex));
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("用户在取消准备时出现了错误");
        }

    }
}
