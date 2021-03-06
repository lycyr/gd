package com.gameserver.gd.controller;

import com.gameserver.gd.entity.Card;
import com.gameserver.gd.pvp.CardList;
import com.gameserver.gd.pvp.Hall;
import com.gameserver.gd.pvp.Room;
import com.gameserver.gd.pvp.Winner;
import com.gameserver.gd.service.PVPService;
import com.gameserver.gd.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(PVPService.class);

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

    //获取第二局/第三局的游戏场景
    @RequestMapping(value = "/get-next",method = RequestMethod.POST)
    public Room NextRoom(int roomindex){
        return pvpService.initRoom(Hall.getRooms().get(roomindex));
    }

    //获取静态保存的卡牌信息，测试用
    @RequestMapping(value = "/get-cards",method = RequestMethod.POST)
    public List<Card> initCard(){
        return CardList.getCards();
    }

    //获取某个玩家的点数，用于更新玩家的数据
    @RequestMapping(value = "/getCardPoint",method = RequestMethod.POST)
    public int getCardPoint(String username,int roomindex){
        if (roomindex<0 || roomindex>35)
            return 0;
        Room room = Hall.getRooms().get(roomindex);
        //获取玩家的相对位置
        int playerPosition = 0;
        if (room.getPlayers().get(1).getUsername().equals(username))
            playerPosition = 1;
        return room.getDuel().getPoint()[playerPosition];
    }

    //用于判定当前回合哪个玩家胜出,返回玩家的用户名,返回none代表平局，没有玩家胜出
    @RequestMapping(value = "/getwinner",method = RequestMethod.POST)
    public String getWinner(int roomindex){
        if (roomindex<0 || roomindex>35)
            return null;
        //当前对局的某一小局结束，进行胜负判定
        return Winner.getWinners().get(roomindex);
    }

    //获取最终胜者
    @RequestMapping(value = "/getlastwinner",method = RequestMethod.POST)
    public String getLast(int roomindex){
        if (roomindex<0 || roomindex>35)
            return "none";
        return pvpService.DuelEnd(roomindex);
    }

    @RequestMapping(value = "/getScore",method = RequestMethod.POST)
    public int getScore(String player,int roomindex){
        if (roomindex<0 || roomindex>35)
            return 0;
        System.out.println(player+"socre:"+pvpService.getScore(player,roomindex));
        return pvpService.getScore(player,roomindex);
    }

    @RequestMapping(value = "/updateHandCard",method = RequestMethod.POST)
    public List<Integer> UpdateHandCard(String player, int roomindex){
        Room room = Hall.getRooms().get(roomindex);
        //获取玩家的相对位置
        int playerPosition = 0;
        if (room.getPlayers().get(1).getUsername().equals(player))
            playerPosition = 1;
        return room.getDuel().getHandCards()[playerPosition];
    }

    //获取某个玩家的近战单位区域数据
    @RequestMapping(value = "/getFrontCards",method = RequestMethod.POST)
    public List<Integer> GetFrontCards(String player,int roomindex){
        Room room = Hall.getRooms().get(roomindex);
        //获取玩家的相对位置
        int playerPosition = 0;
        if (room.getPlayers().get(1).getUsername().equals(player))
            playerPosition = 1;
        return room.getDuel().getFrontCards()[playerPosition];
    }

    //获取某位玩家的手牌数量
    @RequestMapping(value = "/getCardCount",method = RequestMethod.POST)
    public int getCardCount(String player,int roomindex){
        try{
        if (player.isEmpty())
            throw new IllegalArgumentException("这不是一个有效的玩家");
        if (roomindex <0 || roomindex>35)
            throw new IllegalArgumentException("这不是一个有效的房间信息");
        return pvpService.getCardCount(player,roomindex);
        }catch (IllegalArgumentException e){
            logger.info(e.getMessage());
            return 0;
        }
    }
}