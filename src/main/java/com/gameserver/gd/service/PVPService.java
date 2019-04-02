package com.gameserver.gd.service;

import com.gameserver.gd.pvp.Duel;
import com.gameserver.gd.pvp.Hall;
import com.gameserver.gd.pvp.Room;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Api("用于处理大厅相关机制")
@Service
public class PVPService {

    public void initScene(Room room){
        Duel duel = new Duel();
        //设置初始分数
        duel.setScore(new int[]{2,2});
        //设置场地近战单位
        duel.setFrontCards(new List[]{new ArrayList<Integer>(),new ArrayList<Integer>()});
        //设置场地远程单位
        duel.setBehindCards(new List[]{new ArrayList<Integer>(),new ArrayList<Integer>()});
        //设置玩家的手牌
        duel.setHandCards(new List[]{new ArrayList<Integer>(),new ArrayList<Integer>()});
        //设置玩家的卡组
        duel.setDecks(new List[]{new ArrayList<Integer>(),new ArrayList<Integer>()});
        for (int i= 0;i < 5;i++){

        }
    }
}
