package com.gameserver.gd.pvp;

import com.gameserver.gd.entity.User;
//记录玩家的用户名以及当前所在的房间号
public class UserVO {
    private String username;
    private int roomindex;

    public UserVO(String username,int roomindex){
        //-1代表此玩家尚未进入游戏房间
        this.roomindex = -1;
        this.username = username;
    }

    public int getRoomindex() {
        return roomindex;
    }

    public void setRoomindex(int roomindex) {
        this.roomindex = roomindex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return username+"已登录，初始化房间编号数据为"+roomindex;
    }

}