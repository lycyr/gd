package com.gameserver.gd.service;

import com.gameserver.gd.entity.Card;
import com.gameserver.gd.pvp.*;
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
        //设置初始点数
        duel.setPoint(new int[]{0,0});
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
        //读取用户牌组，以list的形式记录下来
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

    //id 卡牌编号，type 卡牌类型，position 卡牌放置位置。出牌操作，将卡牌加入到场景中去。
    public boolean addCard(String player,int roomindex,int id,int type,int position){
        Room room = Hall.getRooms().get(roomindex);
        //获取玩家的相对位置
        int playerPosition = 0;
        if (room.getPlayers().get(1).getUsername().equals(player))
            playerPosition = 1;
        //根据牌的类型进行分情况处理
        if (type == 0){
            //先删除手牌
            if (room.getDuel().getHandCards()[playerPosition].size()>0){
                List<Integer> arrayList = room.getDuel().getHandCards()[playerPosition];
                for(Integer i : arrayList){
                    if (i == id){
                        arrayList.remove(i);
                        break;
                    }
                }
                //然后将此牌加入场景中
                room.getDuel().getFrontCards()[playerPosition].set(position,id);
                //进行点数变更
                modifyScore(room,playerPosition,id);
                return true;
            }
            return false;
        }
        else if(type == 1){
            if (room.getDuel().getHandCards()[playerPosition].size()>0){
                List<Integer> arrayList = room.getDuel().getHandCards()[playerPosition];
                for(Integer i : arrayList){
                    if (i == id){
                        arrayList.remove(i);
                        break;
                    }
                }
                room.getDuel().getBehindCards()[playerPosition].set(position,id);
                modifyScore(room,playerPosition,id);
                return true;
            }
            return false;
        }
        else
            return false;
    }

    //房间，playerposition玩家的相对位置，id 卡牌编号
    public void modifyScore(Room room,int playerposition,int id){
        int score = room.getDuel().getPoint()[playerposition];
        List<Card> cardList = CardList.getCards();
        for (Card c : cardList){
            if (c.getIdcards() == id){
                score += c.getCardpoint();
                break;
            }
        }
        room.getDuel().getPoint()[playerposition] = score;
    }

    //计算某个玩家的分数
    public int calScore(String player,int roomindex){
        Room room = Hall.getRooms().get(roomindex);
        //获取玩家的相对位置
        int playerPosition = 0;
        if (room.getPlayers().get(1).getUsername().equals(player))
            playerPosition = 1;
        List<Integer> close = room.getDuel().getFrontCards()[playerPosition];
        List<Integer> far = room.getDuel().getBehindCards()[playerPosition];
        int score = 0;
        List<Card> cardList = CardList.getCards();
        //如果当前位置为0，说明此处没有卡牌，跳过对此卡的处理
        for (Integer i :close){
            if (i!=0){
                for (Card c : cardList){
                    if (c.getIdcards() == i){
                        score += c.getCardpoint();
                        break;
                    }
                }
            }
        }
        for (Integer i :far){
            if (i!=0){
                for (Card c : cardList){
                    if (c.getIdcards() == i){
                        score += c.getCardpoint();
                        break;
                    }
                }
            }
        }
        room.getDuel().getPoint()[playerPosition] = score;
        return score;
    }
}
