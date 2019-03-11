package com.gameserver.gd.entity;

public class DeckKey {
    private String username;

    private Integer idcards;

    public DeckKey(String username, Integer idcards) {
        this.username = username;
        this.idcards = idcards;
    }

    public DeckKey() {
        super();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Integer getIdcards() {
        return idcards;
    }

    public void setIdcards(Integer idcards) {
        this.idcards = idcards;
    }
}