package com.gameserver.gd.pvp;

import com.gameserver.gd.entity.User;

public class UserVO {
    private String token;
    private int roomindex;

    public UserVO(String token,int roomindex){
        //-1代表此玩家尚未进入游戏房间
        this.roomindex = -1;
        this.token = token;
    }

    public int getRoomindex() {
        return roomindex;
    }

    public void setRoomindex(int roomindex) {
        this.roomindex = roomindex;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token+"在第"+roomindex+"个房间里面";
    }

}
