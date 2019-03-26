package com.gameserver.gd.service;

import com.gameserver.gd.pvp.Room;
import com.gameserver.gd.pvp.UserVO;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

@Api("用于处理房间相关逻辑")
@Service
public class RoomService {

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
        }
    }

    //房间内用户准备战斗时
    public String ready(Room room){
        room.readyCount();
        if (room.getPlayers().size()==2)
            return "房间里面的两人都已准备，即将进入对战页面";
        else
            return "请耐心等待另一人准备";
    }

    //有人取消准备时执行此操作
    public void cannel(Room room){
        room.cancellReady();
    }
}
