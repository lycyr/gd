package com.gameserver.gd.pvp;

import com.gameserver.gd.entity.User;

import java.util.List;

//用于记录玩家的信息
public class Player {
    private User user;
    private int roomIndex;

    public Player(User user) {
        this.user = user;
        this.roomIndex = -1;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRoomIndex() {
        return roomIndex;
    }

    public void setRoomIndex(int roomIndex) {
        this.roomIndex = roomIndex;
    }
}
