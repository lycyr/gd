package com.gameserver.gd.service;

import com.gameserver.gd.entity.Card;
import com.gameserver.gd.entity.Deck;
import com.gameserver.gd.entity.DeckExample;
import com.gameserver.gd.mapper.DeckMapper;
import com.gameserver.gd.pvp.CardList;
import com.gameserver.gd.pvp.MyDeck;
import com.gameserver.gd.pvp.NewDeck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class DeckService {
    private DeckMapper deckMapper;

    @Autowired
    public DeckService(DeckMapper deckMapper){
        this.deckMapper = deckMapper;
    }

    //以Map的形式返回用户的卡组
    public Map<Integer, Integer> GetDeckByUsername(String username){
        DeckExample deckExample = new DeckExample();
        deckExample.or().andUsernameEqualTo(username);
        List<Deck> deckList = deckMapper.selectByExample(deckExample);
        HashMap<Integer,Integer> myDeck = new HashMap<>();
        for (Deck d :deckList){
            myDeck.put(d.getIdcards(),d.getCount());
        }
        return myDeck;
    }

    //以内含两个ArrayList的对象MyDeck的形式返回数据,返回到unity的时候会以json的形式发送
    public MyDeck GetDeckByUsernameAnother(String username){
        DeckExample deckExample = new DeckExample();
        deckExample.or().andUsernameEqualTo(username);
        List<Deck> deckList = deckMapper.selectByExample(deckExample);
        MyDeck myDeck = new MyDeck();
        myDeck.setCardId(new ArrayList<>());
        myDeck.setCount(new ArrayList<>());
        for (Deck d : deckList){
            myDeck.getCardId().add(d.getIdcards());
            myDeck.getCount().add(d.getCount());
        }
        return myDeck;
    }

      //新建一个套牌，用于新的玩家
    @Transactional
    public boolean SetNewDeck1(NewDeck newDeck){
        int len = newDeck.getDecks().size();
        Map<Integer,Integer> decks = new HashMap<>();
        for (int i=0;i<len;i++){
            decks.put(newDeck.getDecks().get(i),decks.getOrDefault(newDeck.getDecks().get(i),0)+1);
        }
        try{
            Deck deck = new Deck();
            deck.setUsername(newDeck.getUsername());
            for(Map.Entry<Integer,Integer> entry : decks.entrySet()){
                deck.setIdcards(entry.getKey());
                deck.setCount(entry.getValue());
                if (deckMapper.insert(deck)<=0)
                    return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Transactional
    public boolean SetNewDeck(NewDeck newDeck){
        int len = newDeck.getDecks().size();
        if (len != 20){
            System.out.println("卡组数目不足，请重新创建套牌");
            return false;
        }
        Map<Integer,Integer> decks = new HashMap<>();
        for (int i=0;i<len;i++){
            decks.put(newDeck.getDecks().get(i),decks.getOrDefault(newDeck.getDecks().get(i),0)+1);
        }
        List<Card> cardList = CardList.getCards();
        int functionCards = 0;
        //此方法需要循环卡牌数量次循环比较费时，故替换为另一种方案
//        for (Card c : cardList){
//            if (Integer.valueOf(c.getCardtype())!=2 && decks.get(c.getIdcards())<=3)
//                continue;
//            else if (Integer.valueOf(c.getCardtype())==2 && decks.get(c.getIdcards())<=1){
//                functionCards += 1;
//            }
//            else
//                return false;
//        }
        //判断用户的卡组是否符合组卡规则
        int num=0;
        for (Integer i : decks.keySet()){
            //获取卡牌编号为i的卡的数量
            num = decks.get(i);
            //判断是否符合卡组规则
            if (Integer.valueOf(cardList.get(i-1).getCardtype())!=2 && num <= 3)
                functionCards += 0;
            else if (Integer.valueOf(cardList.get(i-1).getCardtype())==2 && num<=1)
                functionCards += 1;
            else
                return false;
        }
        if (functionCards > 4){
            System.out.println("功能牌数目不符合标准，请重新创建套牌");
            return false;
        }
        try{
            Deck deck = new Deck();
            deck.setUsername(newDeck.getUsername());
            for(Map.Entry<Integer,Integer> entry : decks.entrySet()){
                deck.setIdcards(entry.getKey());
                deck.setCount(entry.getValue());
                if (deckMapper.insert(deck)<=0)
                    return false;
            }
        }catch (Exception e){
            return false;
        }
        return true;
    }

    //进行更新用户的卡组，先删除，后新建的方法
    @Transactional
    public boolean UpdateDeck(NewDeck newDeck){
        if (CheckNewDeck(newDeck)) {
            if (DeleteDeck(newDeck.getUsername())) {
                return SetNewDeck1(newDeck);
            }
            return false;
        }
        else
            return false;
    }

    public boolean DeleteDeck(String username){
        DeckExample deckExample = new DeckExample();
        deckExample.or().andUsernameEqualTo(username);
        if (deckMapper.selectByExample(deckExample).size()==0)
            return true;
        return deckMapper.deleteByExample(deckExample)>0;
    }

    //检测新卡组是否符合组卡规则
    public boolean CheckNewDeck(NewDeck newDeck){
        int len = newDeck.getDecks().size();
        if (len != 20){
            System.out.println("卡组数目不足，请重新创建套牌");
            return false;
        }
        Map<Integer,Integer> decks = new HashMap<>();
        for (int i=0;i<len;i++){
            decks.put(newDeck.getDecks().get(i),decks.getOrDefault(newDeck.getDecks().get(i),0)+1);
        }
        List<Card> cardList = CardList.getCards();
        int functionCards = 0;
        //判断用户的卡组是否符合组卡规则
        int num=0;
        for (Integer i : decks.keySet()){
            //获取卡牌编号为i的卡的数量
            num = decks.get(i);
            //判断是否符合卡组规则
            if (Integer.valueOf(cardList.get(i-1).getCardtype())!=2 && num <= 3)
                functionCards += 0;
            else if (Integer.valueOf(cardList.get(i-1).getCardtype())==2 && num<=1)
                functionCards += 1;
            else{
                System.out.println("有卡不符合卡组套牌上限");
                return false;
            }
        }
        if (functionCards > 4){
            System.out.println("功能牌数目不符合标准，请重新创建套牌");
            return false;
        }
        return true;
    }
}