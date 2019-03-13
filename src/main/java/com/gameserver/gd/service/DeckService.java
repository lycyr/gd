package com.gameserver.gd.service;

import com.gameserver.gd.entity.Deck;
import com.gameserver.gd.entity.DeckExample;
import com.gameserver.gd.mapper.DeckMapper;
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
//    @Transactional
//    public boolean SetNewDeck(String username,MyDeck myDeck){
//        int len = myDeck.getCardId().size();
//        Deck deck = new Deck();
//        deck.setUsername(username);
//        for (int i=0; i<len; i++){
//            deck.setIdcards(myDeck.getCardId().get(i));
//            deck.setCount(myDeck.getCount().get(i));
//            deckMapper.insert(deck);
//        }
//        return true;
//    }

    @Transactional
    public boolean SetNewDeck(NewDeck newDeck){
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

        public boolean DeleteDeck(String username){
        DeckExample deckExample = new DeckExample();
        deckExample.or().andUsernameEqualTo(username);
        return deckMapper.deleteByExample(deckExample)>0;
    }
}
