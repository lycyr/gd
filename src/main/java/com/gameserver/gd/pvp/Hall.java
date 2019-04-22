package com.gameserver.gd.pvp;

import java.util.ArrayList;
import java.util.List;

//大厅类，管理房间
public class Hall {
    //设置为static volatile，可以让进程间共享，从而实现对战功能
    private static volatile List<Room> rooms = new ArrayList<>();

    static {
        for (int i = 0; i < 36; i++) {
            Room room = new Room();
            room.setRoomindex(i);
            Hall.rooms.add(room);
        }
    }

    public static List<Room> getRooms() {
        return rooms;
    }

    private static void setRooms(List<Room> rooms) {
        Hall.rooms = rooms;
    }
}
