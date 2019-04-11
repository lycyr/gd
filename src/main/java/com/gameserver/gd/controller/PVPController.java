package com.gameserver.gd.controller;

import com.gameserver.gd.entity.Card;
import com.gameserver.gd.pvp.CardList;
import com.gameserver.gd.pvp.Hall;
import com.gameserver.gd.pvp.Room;
import com.gameserver.gd.service.PVPService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api("主要对pvp中的数据进行测试")
@RequestMapping("/pvp")
public class PVPController {

    private PVPService pvpService;

    @Autowired
    public PVPController(PVPService pvpService){
        this.pvpService = pvpService;
    }

    //获取初始化的游戏场景
    @RequestMapping(value = "/get-init",method = RequestMethod.POST)
    public Room initRoom(int roomindex){
        //pvpService.initScene(Hall.getRooms().get(roomindex));
        return Hall.getRooms().get(roomindex);
    }

    @RequestMapping(value = "/get-cards",method = RequestMethod.POST)
    public List<Card> initRoom(){
        //pvpService.initScene(Hall.getRooms().get(roomindex));
        return CardList.getCards();
    }

    @RequestMapping(value = "/getscore",method = RequestMethod.POST)
    public int getScore(String username,int roomindex){
        if (roomindex<0 || roomindex>35)
            return 0;
        Room room = Hall.getRooms().get(roomindex);
        //获取玩家的相对位置
        int playerPosition = 0;
        if (room.getPlayers().get(1).getUsername().equals(username))
            playerPosition = 1;
        return room.getDuel().getPoint()[playerPosition];
        //return pvpService.calScore(username,roomindex);
    }
}
