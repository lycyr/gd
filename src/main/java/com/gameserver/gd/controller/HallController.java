package com.gameserver.gd.controller;

import com.gameserver.gd.pvp.*;
import com.gameserver.gd.service.HallService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api("用于处理大厅相关逻辑")
@RequestMapping("/hall")
public class HallController {

    private HallService hallService;

    public HallController(HallService hallService){
        this.hallService = hallService;
    }

    //玩家进入大厅时进行调用，且需要通过心跳机制进行数据更新
    @RequestMapping(value = "/list",method = {RequestMethod.POST,RequestMethod.GET})
    public List<RoomInfo> getRoomLists() {
        try {
            //返回所有房间的用户列表
            List<RoomInfo> roomList = new ArrayList<>();
            //获取大厅的所有房间
            List<Room> rooms = Hall.getRooms();
            for (Room room : rooms) {
                RoomInfo roomInfo = new RoomInfo();
                for (UserVO userVO : room.getPlayers()) {
                    roomInfo.getUsername().add(userVO.getUsername());
                }
                //ready是获得当前准备的用户人数
                roomInfo.setReady(room.getReady());
                roomInfo.setRoomindex(room.getRoomindex());
                roomList.add(roomInfo);
            }
            return roomList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //玩家加入某个房间时调用此方法,传入的参数未token（MD5加密值，房间编号）
    @RequestMapping(value = "/join",method = {RequestMethod.GET,RequestMethod.POST})
    public RoomInfo JoinRoom(String token,int roomIndex){
        try{
            UserVO userVO = (UserVO)UserList.get(token);
            if (userVO == null)
                throw new IllegalArgumentException("未登录");
            Room room = Hall.getRooms().get(roomIndex);
            //防止第三个人进入此房间
            if (room.getPlayers().size()==2)
                return null;
            hallService.joinRoom(roomIndex,userVO);
            room = Hall.getRooms().get(roomIndex);
            RoomInfo roomInfo = new RoomInfo();
            roomInfo.setReady(room.getReady());
            for (UserVO userVO1 : room.getPlayers()){
                roomInfo.getUsername().add(userVO1.getUsername());
            }
            return roomInfo;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    //退出房间时使用此方法，需要用户先取消准备，再退出房间。否则会出现逻辑问题导致无法开始对局
    @RequestMapping(value = "/quit-room",method = {RequestMethod.GET,RequestMethod.POST})
    public boolean QuitRoom(String token){
        try {
            UserVO userVO = (UserVO)UserList.get(token);
            if (userVO == null)
                return false;
            hallService.exitRoom(userVO);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
