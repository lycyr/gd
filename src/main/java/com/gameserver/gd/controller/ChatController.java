package com.gameserver.gd.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gameserver.gd.Utils.ChatMessage;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

@ServerEndpoint(value = "/chat/{nickname}")
@Component
public class ChatController {
    //用来存放每个客户端对应的WebSocket对象。
    private static CopyOnWriteArraySet<ChatController> chatControllers = new CopyOnWriteArraySet<>();
    //与客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    //用来记录sessionId和session绑定
    private static Map<String,Session> sessionMap = new HashMap<>();
    //用户的username
    private String nickname;

    @OnOpen
    public void OnOpen(Session session, @PathParam("nickname")String nickname){
        this.session = session;
        this.nickname = nickname;
        sessionMap.put(nickname,session);
        System.out.println(session.getId()+" "+nickname);
        //将此对象加入set中
        chatControllers.add(this);
        this.session.getAsyncRemote().sendText("聊天室连接成功，现在可以正常进行对话");
    }

    @OnClose
    public void onClose() {
        chatControllers.remove(this); //从set中删除
        System.out.println(nickname+"退出房间，关闭聊天室连接");
    }

    //message 客户端发过来的消息
    @OnMessage
    public void onMessage(String message, Session session) {
        //System.out.println("来自客户端的消息:"+nickname+" "+ message);
        ChatMessage cmsg;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            //将数据解析为Msg类型的数据
            cmsg = objectMapper.readValue(message,ChatMessage.class);
            System.out.println(cmsg.toString());
            Session sender = sessionMap.get(cmsg.getSender());
            Session receiver = sessionMap.get(cmsg.getReceiver());
            //System.out.println(receiver.getId()+msg.getReceiver()+" "+sender.getId()+msg.getSender());
            if (receiver != null){
                receiver.getAsyncRemote().sendText(message);
                sender.getAsyncRemote().sendText(message);
            }
            else
                sender.getAsyncRemote().sendText("抱歉，"+cmsg.getReceiver()+"已离线，请稍后再发");
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