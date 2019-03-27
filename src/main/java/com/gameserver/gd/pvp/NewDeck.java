package com.gameserver.gd.pvp;

import java.util.List;
//用于用户新建卡组或者更新卡组，此对象为与客户端对接的api
public class NewDeck {
    private String username;
    private List<Integer> decks;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setDecks(List<Integer> decks) {
        this.decks = decks;
    }

    public List<Integer> getDecks() {
        return decks;
    }
}
