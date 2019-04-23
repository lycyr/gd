package com.gameserver.gd.Utils;

public class ChatMessage {
    //发送者
    private String sender;
    //接收者
    private String receiver;
//    //房间索引
//    private int roomindex;
    //发送的消息
    private String message;

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver() {
        return receiver;
    }

//    public void setRoomindex(int roomindex) {
//        this.roomindex = roomindex;
//    }
//
//    public int getRoomindex() {
//        return roomindex;
//    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return sender+"向"+receiver+"发送消息："+message;
    }
}
