package com.gameserver.gd.service;

import com.gameserver.gd.pvp.Hall;
import com.gameserver.gd.pvp.UserVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Api("用于处理大厅相关逻辑")
@Service
public class HallService {

    private RoomService roomService;

    @Autowired
    public HallService(RoomService roomService){
        this.roomService = roomService;
    }

    //用于玩家进入一个新的游戏房间，roomIndex为房间编号，userVO为具体用户
    public void joinRoom(int roomIndex, UserVO userVO){
        synchronized (Hall.getRooms()){
            if (userVO.getRoomindex()!=-1)
                this.exitRoom(userVO);
            try {
                roomService.addPlayer(Hall.getRooms().get(roomIndex),userVO);
                userVO.setRoomindex(roomIndex);
            } catch (Exception e) {
                System.out.println("用户进入房间失败");
                e.printStackTrace();
            }
        }
    }

    //用户退出房间时使用此方法
    public void exitRoom(UserVO userVO){
        //System.out.println("执行到了hallservice里面的退出房间操作");
        synchronized (Hall.getRooms()){
            if (userVO.getRoomindex() != -1){
                roomService.removePlayer(Hall.getRooms().get(userVO.getRoomindex()),userVO);
                userVO.setRoomindex(-1);
            }
        }
    }
}
