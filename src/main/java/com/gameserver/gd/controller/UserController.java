package com.gameserver.gd.controller;

import com.gameserver.gd.Utils.Security;
import com.gameserver.gd.entity.User;
import com.gameserver.gd.error.ApiError;
import com.gameserver.gd.error.UnauthenticatedException;
import com.gameserver.gd.pvp.CardList;
import com.gameserver.gd.pvp.UserList;
import com.gameserver.gd.pvp.UserVO;
import com.gameserver.gd.service.CardService;
import com.gameserver.gd.service.HallService;
import com.gameserver.gd.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private HallService hallService;
    private CardService cardService;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserController(UserService userService,HallService hallService,CardService cardService){
        this.userService = userService;
        this.hallService = hallService;
        this.cardService = cardService;
    }

    @RequestMapping(value = "doreg",method = {RequestMethod.POST,RequestMethod.GET})
    public boolean DoReg(String username,String password,String email){
        if (username == null || password==null || email==null)
            return false;
        try{
            //判断此用户名是否存在，存在的话将返回false
            if (userService.IsExist(username))
                return false;
            return userService.NewUserReg(username,password,email);
        }catch (Exception e){
            throw new IllegalArgumentException("用户注册时出现问题");
        }
    }

    @RequestMapping(value = "/check",method = {RequestMethod.POST,RequestMethod.GET})
    public boolean IsRegistered(String username,String password){

        return userService.IsRegistered(username,password);
    }

    @RequestMapping(value = "/login",method = {RequestMethod.GET,RequestMethod.POST})
    public String Login(String username,String password) throws UnauthenticatedException {
        try{
            //验证此用户是否是已注册用户
            if (userService.IsRegistered(username,password)){
                String encodeStr = Security.md5(username+password);
                UserVO userVO = new UserVO(username,-1);
                System.out.println(encodeStr+" "+userVO.toString());
                //初始化所有卡牌信息
                CardList.setCards(cardService.GetCardInfo());
                //处理记录为已经登录过的用户
                UserVO loginedUser = (UserVO)UserList.get(encodeStr);
                if (loginedUser != null){
                    System.out.println(loginedUser.toString());
                    if (loginedUser.getRoomindex()!=-1)
                        hallService.exitRoom(loginedUser);
                    System.out.println("已成功处理已经登录的用户");
                }
                //将用户添加到当前在线的用户列表中去
                //encodeStr即使用md5加密后的token值
                UserList.setUserList(encodeStr,userVO);
                return encodeStr;
            }
            else
                return "failed";
        }catch (Exception e){
            logger.info("在用户"+username+"登录时出现了异常");
            throw new UnauthenticatedException(new ApiError(ApiError.ErrorType.ERR_INCORRECT_USERNAME_OR_PASSWORD));
        }
    }
}