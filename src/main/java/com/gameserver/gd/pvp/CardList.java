package com.gameserver.gd.pvp;

import com.gameserver.gd.entity.Card;
import com.gameserver.gd.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CardList{
    private static volatile List<Card> cards;
//    private CardService cardService;
//    @Autowired
//    public CardList(CardService cardService){
//        this.cardService = cardService;
//    }
//    static {
//        cards = CardService.
//    }

    public static void setCards(List<Card> cards) {
        CardList.cards = cards;
    }

    public static List<Card> getCards() {
        return cards;
    }
}
