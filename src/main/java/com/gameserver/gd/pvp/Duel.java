package com.gameserver.gd.pvp;

import com.gameserver.gd.entity.Card;

import java.util.List;

//用于进行定义决斗场景
public class Duel {
    //用户的初始点数，清零即为失败
    private int []score = new int[]{2,2};
    //用户的初始化的手牌
    private List<Integer>[] handCards;
    //游戏场地近战单位区域
    private List<Integer>[] frontCards;
    //游戏场地远程单位区域
    private List<Integer>[] BehindCards;
    //玩家的主卡组
    private List<Integer>[] decks;
    //当前玩家判定
    private int CurPlayer;

    public void setCurPlayer(int curPlayer) {
        CurPlayer = curPlayer;
    }

    public int getCurPlayer() {
        return CurPlayer;
    }

    public int[] getScore() {
        return score;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

    public List<Integer>[] getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Integer>[] handCards) {
        this.handCards = handCards;
    }

    public List<Integer>[] getFrontCards() {
        return frontCards;
    }

    public void setFrontCards(List<Integer>[] frontCards) {
        this.frontCards = frontCards;
    }

    public List<Integer>[] getBehindCards() {
        return BehindCards;
    }

    public void setBehindCards(List<Integer>[] behindCards) {
        BehindCards = behindCards;
    }

    public void setDecks(List<Integer>[] decks) {
        this.decks = decks;
    }

    public List<Integer>[] getDecks() {
        return decks;
    }
}
