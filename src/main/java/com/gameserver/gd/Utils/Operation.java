package com.gameserver.gd.Utils;

public class Operation {
    //卡牌id
    private int id;
    //卡片类型
    private int type;
    //出卡放置的位置
    private int position;
    //当前操作
    private String opera;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setOpera(String operation) {
        this.opera = operation;
    }

    public String getOpera() {
        return opera;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "id为"+id+"在第"+type+"行第"+position+"个位置，且状态为："+opera;
    }
}