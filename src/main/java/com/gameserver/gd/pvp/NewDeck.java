package com.gameserver.gd.pvp;

import java.util.List;

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
