package com.gameserver.gd.entity;

public class Card {
    private Integer idcards;

    private String cardname;

    private Integer cardpoint;

    private String cardtype;

    private String cardinfo;

    public Card(Integer idcards, String cardname, Integer cardpoint, String cardtype, String cardinfo) {
        this.idcards = idcards;
        this.cardname = cardname;
        this.cardpoint = cardpoint;
        this.cardtype = cardtype;
        this.cardinfo = cardinfo;
    }

    public Card() {
        super();
    }

    public Integer getIdcards() {
        return idcards;
    }

    public void setIdcards(Integer idcards) {
        this.idcards = idcards;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String cardname) {
        this.cardname = cardname == null ? null : cardname.trim();
    }

    public Integer getCardpoint() {
        return cardpoint;
    }

    public void setCardpoint(Integer cardpoint) {
        this.cardpoint = cardpoint;
    }

    public String getCardtype() {
        return cardtype;
    }

    public void setCardtype(String cardtype) {
        this.cardtype = cardtype == null ? null : cardtype.trim();
    }

    public String getCardinfo() {
        return cardinfo;
    }

    public void setCardinfo(String cardinfo) {
        this.cardinfo = cardinfo == null ? null : cardinfo.trim();
    }
}