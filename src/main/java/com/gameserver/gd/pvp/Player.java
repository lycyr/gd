package com.gameserver.gd.pvp;

import com.gameserver.gd.entity.User;

import java.util.List;

//用于记录玩家的信息
public class Player {
    private String username;

    public Player(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}