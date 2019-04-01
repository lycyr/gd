package com.gameserver.gd.pvp;

import java.util.ArrayList;
import java.util.List;
//房间信息，返回给客户端，用户名以及准备人数
public class RoomInfo {
    //存储用户名
    private List<String> username = new ArrayList<>();
    //存储当前房间的准备人数
    private int ready = 0;
    //房间的id
    private int roomindex;

    public void setRoomindex(int roomindex) {
        this.roomindex = roomindex;
    }

    public int getRoomindex() {
        return roomindex;
    }

    public void setReady(int ready) {
        this.ready = ready;
    }

    public int getReady() {
        return ready;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public List<String> getUsername() {
        return username;
    }
}
