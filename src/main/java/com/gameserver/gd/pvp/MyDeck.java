package com.gameserver.gd.pvp;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

//用于返回当前用户的套牌，Map的形式
public class MyDeck {
    public ArrayList<Integer> cardId;
    public ArrayList<Integer> count;

    public void setCount(ArrayList<Integer> count) {
        this.count = count;
    }

    public ArrayList<Integer> getCount() {
        return count;
    }

    public void setCardId(ArrayList<Integer> cardId) {
        this.cardId = cardId;
    }

    public ArrayList<Integer> getCardId() {
        return cardId;
    }
}
