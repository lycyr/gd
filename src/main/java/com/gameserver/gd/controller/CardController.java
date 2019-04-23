package com.gameserver.gd.controller;

import com.gameserver.gd.entity.Card;
import com.gameserver.gd.service.CardService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/card")
@Api("用来处理卡组（“背包系统”）")
public class CardController {
    private CardService cardService;

    @Autowired
    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    @RequestMapping(value = "/getInfo",method = {RequestMethod.GET,RequestMethod.POST})
    public Card getInfo(int id){
        if ( id < 0 )
            return null;
        try {
            return cardService.GetCardInfoById(id);
        }catch (Exception e){
            throw new IllegalArgumentException("这是不存在(未获得)的卡牌");
        }

    }

    @RequestMapping(value = "/getAll",method = {RequestMethod.GET,RequestMethod.POST})
    public List<Card> getAll(){
        try {
            return  cardService.GetCardInfo();
        }catch (Exception e){
            throw new IllegalArgumentException("服务器故障，请重新登录");
        }

    }
}