package com.gameserver.gd.service;

import com.gameserver.gd.entity.Card;
import com.gameserver.gd.pvp.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
        //设置功能卡单位
        duel.setFunctionCards(new int[]{0,0});
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
        duel.setCurPlayer(Math.abs(new Random().nextInt(2)));
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
            //进行随机化牌组
            Collections.shuffle(arrayList,new Random());
            //进行随机初始化手牌
            if(arrayList.size()<=8) {
                duel.getHandCards()[i] = arrayList;
            }
            else {
                for (int m=0;m<8;m++){
                    //修改伪随机方法
                    duel.getHandCards()[i].add(arrayList.remove(Math.abs(new Random().nextInt(arrayList.size()))));
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

    //id 卡牌编号，type 卡牌类型，position 卡牌放置位置。出牌操作，将卡牌加入到场景中去。
    public synchronized boolean addCardAnother(String player,int roomindex,int id,int type,int position){
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
                //先获取最后一个id为0的位置，然后将此牌加入场景中
                position = room.getDuel().getFrontCards()[playerPosition].lastIndexOf(0);
                if (position < 0 || position > 4)
                    return false;
                room.getDuel().getFrontCards()[playerPosition].set(position,id);
                //进行点数变更
                modifyScore(room,playerPosition,id);
                return true;
            }
            return false;
        }
        else if (type == 1){
            if (room.getDuel().getHandCards()[playerPosition].size()>0){
                List<Integer> arrayList = room.getDuel().getHandCards()[playerPosition];
                for(Integer i : arrayList){
                    if (i == id){
                        arrayList.remove(i);
                        break;
                    }
                }
                //先获取最后一个id为0的位置，然后将此牌加入场景中
                position = room.getDuel().getBehindCards()[playerPosition].lastIndexOf(0);
                if (position < 0 || position > 4)
                    return false;
                room.getDuel().getBehindCards()[playerPosition].set(position,id);
                modifyScore(room,playerPosition,id);
                return true;
            }
            return false;
        }
        else if (type == 2){
            //先从手牌中删除此卡
            if (room.getDuel().getHandCards()[playerPosition].size()>0){
                List<Integer> arrayList = room.getDuel().getHandCards()[playerPosition];
                for(Integer i : arrayList){
                    if (i == id){
                        arrayList.remove(i);
                        break;
                    }
                }
                room.getDuel().getFunctionCards()[playerPosition] = id;
            }
            else
                return false;
            switch (id){
                case 11:
                    return card11(playerPosition,room);
                case 12:
                    return card12(playerPosition,room);
                case 13:
                    return card13(playerPosition,room);
                case 14:
                    return card14(playerPosition,room);
                default:
                        return false;
            }
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

    //用于判定当前回合哪个玩家胜出,返回玩家的用户名
    public String WinORFailure(int roomindex){
        Room room = Hall.getRooms().get(roomindex);
        int []point = room.getDuel().getPoint();
        if (point[0]<point[1]){
            return room.getPlayers().get(1).getUsername();
        }
        else if (point[0]>point[1]){
            return room.getPlayers().get(0).getUsername();
        }
        else{
            return "none";
        }
    }

    //用于判断对局结束时，最终的胜利者是谁，同时进行房间的初始化操作
    public synchronized String DuelEnd(int roomindex){
        String username;
        Room room = Hall.getRooms().get(roomindex);
        //进行更新玩家的生命值
        //进行查询最终胜者
        if (room.getDuel().getScore()[0] == 0)
            username = room.getPlayers().get(1).getUsername();
        else if (room.getDuel().getScore()[1] == 0)
            username = room.getPlayers().get(0).getUsername();
        else
            username = "none";
        //整个对局结束时，将对战场置为空
        //room.setDuel(null);
        //对局结束时，将房间准备数置为0（便于此两人进行下一次对局）
        room.setReady(0);
        return username;
    }

    //初始化玩家数据，用于第二局/第三局对战
    public synchronized Room initRoom(Room room){
        Duel duel = room.getDuel();
        //进行更新玩家的生命值
        if (duel.getPoint()[0]<duel.getPoint()[1]){
            room.getDuel().getScore()[0] -= 1;
            Winner.getWinners().set(room.getRoomindex(),room.getPlayers().get(1).getUsername());
        }
        else if (duel.getPoint()[0]>duel.getPoint()[1]){
            room.getDuel().getScore()[1] -= 1;
            Winner.getWinners().set(room.getRoomindex(),room.getPlayers().get(0).getUsername());
        }
        //设置初始点数
        duel.setPoint(new int[]{0,0});
        //初始化功能卡
        duel.setFunctionCards(new int[]{0,0});
        //初始化远程单位与近战单位
        for (int i= 0;i < 5;i++){
            duel.getFrontCards()[0].set(i,0);
            duel.getFrontCards()[1].set(i,0);
            duel.getBehindCards()[0].set(i,0);
            duel.getBehindCards()[1].set(i,0);
        }
        //设置先手玩家0,1
        duel.setCurPlayer(Math.abs(new Random().nextInt(2)));
        return room;
    }

    //获取玩家的当前生命值
    public int getScore(String player,int roomindex){
        Room room = Hall.getRooms().get(roomindex);
        //获取玩家的相对位置
        int playerPosition = 0;
        if (room.getPlayers().get(1).getUsername().equals(player))
            playerPosition = 1;
        int score = room.getDuel().getScore()[playerPosition];
        return score;
    }

    //特殊效果牌11添加成功:丢弃一张牌，随机抽0-3张牌（如果玩家没牌，则不丢弃；如果抽牌总数会超过8张，则多的将不会抽取）
    private boolean card11(int playerPosition,Room room){
        List<Integer> card = room.getDuel().getHandCards()[playerPosition];
        if (card.size()>0)
            card.remove(new Random().nextInt(card.size()));
        int num = new Random().nextInt(4);
        System.out.println("该用户抽了"+num+"张新的手牌");
        List<Integer> deck = room.getDuel().getDecks()[playerPosition];
        for (int i = 0;i < num; i++){
            if (card.size()<8){
                if (deck.size()>=1)
                    card.add(deck.remove(0));
            }
        }
        return true;
    }

    //特殊效果牌12添加:抽1张牌
    private boolean card12(int playerPosition,Room room){
        List<Integer> card = room.getDuel().getHandCards()[playerPosition];
        List<Integer> deck = room.getDuel().getDecks()[playerPosition];
        if (deck.size()>0)
            card.add(deck.remove(0));
        return true;
    }

    //特殊效果牌13添加:敌人抽一张牌，自己抽两张牌（尚不确认是让对手爆牌还是不做处理）
    private boolean card13(int playerPosition,Room room){
        try{
            List<Integer> card = room.getDuel().getHandCards()[playerPosition];
            List<Integer> deck = room.getDuel().getDecks()[playerPosition];
            //自己抽牌
            for (int i=0;i<2;i++){
                if (deck.size()>0 && card.size()<8)
                    card.add(deck.remove(0));
            }
            //对手抽牌
            List<Integer> cardAnother = room.getDuel().getHandCards()[1-playerPosition];
            if (cardAnother.size()<8)
                cardAnother.add(room.getDuel().getDecks()[1-playerPosition].remove(0));
            return true;
        }
        catch (Exception e){
            throw new IllegalArgumentException("卡牌13出牌操作失败");
        }

    }

    //特殊效果牌14添加:丢弃所有手牌，然后抽取相同数量的牌，如果数量不足将抽光牌组。
    private boolean card14(int playerPosition,Room room){
        List<Integer> card = room.getDuel().getHandCards()[playerPosition];
        int num = card.size();
        List<Integer> deck = room.getDuel().getDecks()[playerPosition];
        //获取卡组剩余的卡牌数
        int delay = deck.size();
        if (num >= delay){
            card = deck;
            deck = new ArrayList<Integer>();
        }
        else{
            for (int i=0;i<num;i++){
                card.set(i,deck.remove(0));
            }
        }
        return true;
    }
}