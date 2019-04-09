package com.gameserver.gd.service;

import com.gameserver.gd.pvp.Duel;
import com.gameserver.gd.pvp.Hall;
import com.gameserver.gd.pvp.MyDeck;
import com.gameserver.gd.pvp.Room;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Api("用于处理大厅相关机制")
@Service
public class PVPService {

    private DeckService deckService;

    @Autowired
    public PVPService(DeckService deckService){
        this.deckService = deckService;
    }

    //初始化某个对战房间
    public Room initScene(Room room){
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
        //初始化远程单位与近战单位
        for (int i= 0;i < 5;i++){
            duel.getFrontCards()[0].add(0);
            duel.getFrontCards()[1].add(0);
            duel.getBehindCards()[0].add(0);
            duel.getBehindCards()[1].add(0);
        }
        //设置先手玩家0,1
        duel.setCurPlayer(Math.abs(new Random(47).nextInt(2)));
        //读取用户牌组
        for (int i=0;i<2;i++){
            String username = room.getPlayers().get(i).getUsername();
            MyDeck myDeck = deckService.GetDeckByUsernameAnother(username);
            int len = myDeck.getCardId().size();
            ArrayList<Integer> arrayList = new ArrayList<>();
            for (int j = 0; j < len ;j++){
                for (int k = 0;k<myDeck.getCount().get(j);k++)
                    arrayList.add(myDeck.getCardId().get(j));
            }
            //初始化用户的牌组（修正，先抽出手牌，再记录卡组）
            //duel.getDecks()[i] = arrayList;
            //进行随机初始化手牌
            if(arrayList.size()<=8) {
                duel.getHandCards()[i] = arrayList;
            }
            else {
                for (int m=0;m<8;m++){
                    duel.getHandCards()[i].add(arrayList.remove(Math.abs(new Random(47).nextInt(arrayList.size()))));
                }
                duel.getDecks()[i] = arrayList;
            }
        }
        room.setDuel(duel);
        return room;
    }
}
