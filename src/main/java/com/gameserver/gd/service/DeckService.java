package com.gameserver.gd.service;

import com.gameserver.gd.entity.Deck;
import com.gameserver.gd.entity.DeckExample;
import com.gameserver.gd.mapper.DeckMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
