package com.gameserver.gd.pvp;

import java.util.ArrayList;
import java.util.List;
//房间信息，返回给客户端，用户名以及准备人数
public class RoomInfo {
    private List<String> username = new ArrayList<>();
    private int ready = 0;

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
