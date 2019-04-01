package com.gameserver.gd.pvp;

import com.gameserver.gd.entity.User;

import java.util.ArrayList;
import java.util.List;

//房间类，用于进行处理玩家的对战信息
public class Room {
    private int roomindex;
    //记录玩家，上限2个
    private  List<UserVO> players= new ArrayList<>();
    //对战信息
//    private  List<Duel> duels = new ArrayList<>();
    //记录用户的状态，两个都准备时，ready为2，此时开启房间
    private int ready = 0;

    public void setRoomindex(int roomindex) {
        this.roomindex = roomindex;
    }

    public int getRoomindex() {
        return roomindex;
    }

    //当玩家进行准备或者取消准备时调用此函数，加锁以便进行线程同步
    public synchronized void readyCount(){
        ready=ready+1;
    }
    public synchronized void cancellReady(){
        ready-=1;
    }

    public void setReady(int ready) {
        this.ready = ready;
    }

    public int getReady() {
        return ready;
    }

    public List<UserVO> getPlayers() {
        return players;
    }

    public void setPlayers(List<UserVO> players) {
        this.players = players;
    }

}
