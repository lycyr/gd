package com.gameserver.gd.pvp;

//玩家对战状态枚举类
public enum State{
    Ready("ready"),//准备状态（等待另一名玩家出卡）
    PutCard("putcard"),//轮流出卡阶段
    Skip("skip"),//跳过出卡阶段，当前用户放弃出卡时进入此状态
    PutCardUntil("putcarduntil");//用户处于一直出卡阶段，当对手放弃出卡进入此状态

    String state;
    State(String state){
        this.state = state;
    }
    public String getState(){
        return state;
    }
}
