package com.gameserver.gd.pvp;

//整局游戏的对战流程
public enum GameState {
    OneBegin("第一局对战开始"),OneBattle("第一局对战进行"),OneEnd("第一局对战结束"),
    TwoBegin("第二局对战开始"),TwoBattle("第二局对战进行"),TwoEnd("第二局对战结束"),
    ThreeBegin("第三局对战开始"),ThreeBattle("第三局对战进行"),ThreeEnd("第三局对战结束");

    public String gameState;

    GameState(String gameState) {
        this.gameState = gameState;
    }

    public String getGameState() {
        return gameState;
    }
}
