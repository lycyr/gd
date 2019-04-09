package com.gameserver.gd.controller;

import com.gameserver.gd.pvp.Hall;
import com.gameserver.gd.pvp.Room;
import com.gameserver.gd.service.PVPService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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


}
