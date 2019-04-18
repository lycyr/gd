package com.gameserver.gd.pvp;

import java.util.ArrayList;
import java.util.List;

public class Winner {
    //设置为static volatile，可以让进程间共享，从而实现对战功能
    private static volatile List<String> winners = new ArrayList<>();

    static {
        for (int i = 0; i < 36; i++) {
            winners.add("none");
        }
    }

    public static void setWinners(List<String> winners) {
        Winner.winners = winners;
    }

    public static List<String> getWinners() {
        return winners;
    }
}
