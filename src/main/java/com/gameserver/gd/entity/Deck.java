package com.gameserver.gd.entity;

public class Deck extends DeckKey {
    private Integer count;

    public Deck(String username, Integer idcards, Integer count) {
        super(username, idcards);
        this.count = count;
    }

    public Deck() {
        super();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}