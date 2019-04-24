package com.gameserver.gd.controller;

import com.gameserver.gd.pvp.MyDeck;
import com.gameserver.gd.pvp.NewDeck;
import com.gameserver.gd.service.DeckService;
import com.gameserver.gd.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/deck")
@RestController
@Api("用于背包机制")
public class DeckController {
    private DeckService deckService;
    private UserService userService;

    @Autowired
    public DeckController(DeckService deckService,UserService userService){
        this.deckService = deckService;
        this.userService = userService;
    }

    @RequestMapping(value = "/getdeck",method = {RequestMethod.GET,RequestMethod.POST})
    public Map<Integer,Integer> GetDeckMap(String username){
//        try{
//
//        }catch (Exception e){
//            throw new IllegalArgumentException("");
//        }
        if (username == null)
            return null;
        return deckService.GetDeckByUsername(username);
    }

    @RequestMapping(value = "/getdeck1",method = {RequestMethod.GET,RequestMethod.POST})
    public MyDeck GetDeckMyDeck(String username){
        if (username == null)
            return null;
        return deckService.GetDeckByUsernameAnother(username);
    }

    @RequestMapping(value = "/deletedeck",method = {RequestMethod.GET,RequestMethod.POST})
    public boolean DeleteDeck(String username){
        if (username == null)
            return false;
        else
            return deckService.DeleteDeck(username);
    }

    @RequestMapping(value = "/createdeck",method = RequestMethod.POST)
    public boolean CreateDeck(@RequestBody NewDeck newDeck){
        try{
            //如果玩家不存在，则返回失败信息
            if (!userService.IsExist(newDeck.getUsername()))
                return false;
            if (newDeck.getDecks().size()<1)
                return false;
            return deckService.SetNewDeck(newDeck);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("没有成功创建新的卡组！");
        }
    }

    @RequestMapping(value = "/updatedeck",method = RequestMethod.POST)
    public boolean Update(@RequestBody NewDeck newDeck){
        try{
            if (!userService.IsExist(newDeck.getUsername()))
                return false;
            if (newDeck.getDecks().size()<1)
                return false;
            return deckService.UpdateDeck(newDeck);
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("没有成功更新用户的卡组！");
        }
    }

}
