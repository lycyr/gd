package com.gameserver.gd.service;

import com.gameserver.gd.entity.Deck;
import com.gameserver.gd.entity.DeckExample;
import com.gameserver.gd.mapper.DeckMapper;
import com.gameserver.gd.pvp.MyDeck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public boolean SetNewDeck(String username,MyDeck myDeck){
        int len = myDeck.getCardId().size();
        Deck deck = new Deck();
        deck.setUsername(username);
        for (int i=0; i<len; i++){
            deck.setIdcards(myDeck.getCardId().get(i));
            deck.setCount(myDeck.getCount().get(i));
            deckMapper.insert(deck);
        }
        return true;
    }

    public boolean DeleteDeck(String username){
        DeckExample deckExample = new DeckExample();
        deckExample.or().andUsernameEqualTo(username);
        return deckMapper.deleteByExample(deckExample)>0;
    }
}
