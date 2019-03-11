package com.gameserver.gd.pvp;

import java.util.ArrayList;
import java.util.List;

//房间类，用于进行处理玩家的对战
public class Room {
    //记录玩家，上限2个
    private  List<Player> players= new ArrayList<>();
    //对战信息
    private  List<Duel> duels = new ArrayList<>();

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Duel> getDuels() {
        return duels;
    }

    public void setDuels(List<Duel> duels) {
        this.duels = duels;
    }
}
