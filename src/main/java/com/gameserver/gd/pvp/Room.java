package com.gameserver.gd.pvp;

import com.gameserver.gd.entity.User;

import java.util.ArrayList;
import java.util.List;

//房间类，用于进行处理玩家的对战
public class Room {
    //记录玩家，上限2个
    private  List<UserVO> players= new ArrayList<>();
    //对战信息
//    private  List<Duel> duels = new ArrayList<>();
    //记录用户的状态，两个都准备时，ready为2，此时开启房间
    private int ready = 0;

    //
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
