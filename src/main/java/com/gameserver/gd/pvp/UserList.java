package com.gameserver.gd.pvp;

import java.util.HashMap;
import java.util.Map;
//记录当前的在线用户
public class UserList {
    private static volatile Map<String,Object> userList = new HashMap<>();

    //获取用户信息
    static public Object get(String token){
        return userList.get(token);
    }

    //将用户加入在线的用户列表中去
    synchronized static public void setUserList(String token,Object o){
        userList.put(token,o);
    }
}
