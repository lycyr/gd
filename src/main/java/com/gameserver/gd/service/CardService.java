package com.gameserver.gd.service;

import com.gameserver.gd.entity.Card;
import com.gameserver.gd.entity.CardExample;
import com.gameserver.gd.mapper.CardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    private CardMapper cardMapper;

    @Autowired
    public CardService(CardMapper cardMapper){
        this.cardMapper = cardMapper;
    }

    public Card GetCardInfoById(int cardId){
        CardExample cardExample = new CardExample();
        cardExample.or().andIdcardsEqualTo(cardId);
        List<Card> cardList = cardMapper.selectByExample(cardExample);
        return cardList.size()>0 ? cardList.get(0):null;
    }
}
