package com.gameserver.gd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameserver.gd.Utils.Msg;
import com.gameserver.gd.service.PVPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/websocket/{nickname}")
@Component
public class WebSocketService {
    //用来存放每个客户端对应的WebSocket对象。
    private static CopyOnWriteArraySet<WebSocketService> webSocketServices = new CopyOnWriteArraySet<>();
    //与客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //用来记录sessionId和session绑定
    private static Map<String,Session> sessionMap = new HashMap<>();
    //用户的username
    private String nickname;
    // 这里使用静态，让 service 属于类
    private static PVPService pvpService;
    
    // 注入的时候，给类的service注入。set方法：静态，非静态的成员变量都可以用set注入。构造器只可以注入非静态的成员变量。这都是spring的基础知识
    @Autowired
    public void SetPVPService(PVPService pvpService){
        WebSocketService.pvpService = pvpService;
    }

    @OnOpen
    public void OnOpen(Session session, @PathParam("nickname")String nickname){
        this.session = session;
        this.nickname = nickname;
        sessionMap.put(nickname,session);
        System.out.println(session.getId()+" "+nickname);
        //将此对象加入set中
        webSocketServices.add(this);
        //this.session.getAsyncRemote().sendText("正在初始化对战场景，请耐心等待");
    }

    @OnClose
    public void onClose() {
        webSocketServices.remove(this); //从set中删除
        System.out.println("有人掉线？对局结束？");
    }

    //message 客户端发过来的消息
    @OnMessage
    public void onMessage(String message, Session session) {
        //System.out.println("来自客户端的消息:"+nickname+" "+ message);
        Msg msg;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            //将数据解析为Msg类型的数据
            msg = objectMapper.readValue(message,Msg.class);
            System.out.println(nickname+"的操作为："+msg.toString());
            Session sender = sessionMap.get(msg.getSender());
            Session receiver = sessionMap.get(msg.getReceiver());
            //System.out.println(receiver.getId()+msg.getReceiver()+" "+sender.getId()+msg.getSender());
            if (receiver != null){
                receiver.getAsyncRemote().sendText(message);
                sender.getAsyncRemote().sendText(message);
                //如果一方放弃出牌，则不对数据进行处理
                if (msg.getOperation().getOpera().equals("End"))
                    System.out.println(msg.getSender()+"放弃出牌");
                //进行处理出牌操作
                else if (!pvpService.addCardAnother(msg.getSender(),msg.getRoomindex(),msg.getOperation().getId(),msg.getOperation().getType(),msg.getOperation().getPosition()))
                    throw new IllegalArgumentException("数据存储出错");
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new IllegalArgumentException("数据解析/转发出错");
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");
        error.printStackTrace();
    }
}