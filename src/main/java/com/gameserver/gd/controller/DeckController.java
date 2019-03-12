package com.gameserver.gd.controller;

import com.gameserver.gd.service.DeckService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/deck")
@RestController
@Api("用于背包机制")
public class DeckController {
    private DeckService deckService;

    @Autowired
    public DeckController(DeckService deckService){
        this.deckService = deckService;
    }

    @RequestMapping(value = "/getdeck",method = {RequestMethod.GET,RequestMethod.POST})
    public Map<Integer,Integer> GetDeckMap(String username){
        if (username == null)
            return null;
        return deckService.GetDeckByUsername(username);
    }

}
