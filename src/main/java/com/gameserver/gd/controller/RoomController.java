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
    public String doReady(int roomindex){
        if (roomindex <0 || roomindex>=36)
            return "error！错误原因：这是一个无效的房间号，请重新登录";
        if (Hall.getRooms().get(roomindex).getReady() == 2)
            return "房间里面的两人都已准备，即将进入对战页面";
        return roomService.ready(Hall.getRooms().get(roomindex));
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
