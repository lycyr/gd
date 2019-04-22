package com.gameserver.gd.service;

import com.gameserver.gd.pvp.Room;
import com.gameserver.gd.pvp.UserVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Api("用于处理房间相关逻辑")
@Service
public class RoomService {

    private PVPService pvpService;

    @Autowired
    public RoomService(PVPService pvpService){
        this.pvpService = pvpService;
    }

    //当有用户进入房间时，将其添加
    synchronized public void addPlayer(Room room, UserVO userVO)throws  Exception{
        synchronized (room){
            if (room.getPlayers().size()<2)
                room.getPlayers().add(userVO);
            else
                throw new IllegalArgumentException("该房间用户已满");
        }
    }

    //当有用户退出房间时进行此操作
    public void removePlayer(Room room,UserVO userVO){
        synchronized (room){
            room.getPlayers().remove(userVO);
            //进行对战场景重置，对局结束没有进行duel的重置，在此处进行补偿重置操作
            room.setDuel(null);
        }
    }

    //房间内用户准备战斗时
    public boolean ready(Room room){
        room.readyCount();
        if (room.getPlayers().size()==2){
            pvpService.initScene(room);
            return true;
        }
        else
            return false;
    }

    //有人取消准备时执行此操作
    public void cannel(Room room){
        room.cancellReady();
    }
}
