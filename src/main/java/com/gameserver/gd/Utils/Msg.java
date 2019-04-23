package com.gameserver.gd.Utils;

import io.swagger.annotations.Api;

@Api("用户传输的数据")
public class Msg{
    //发送者
    private String sender;
    //接收者
    private String receiver;
    //房间索引
    private int roomindex;
    //消息内容
    private Operation operation;

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

    public void setRoomindex(int roomindex) {
        this.roomindex = roomindex;
    }

    public int getRoomindex() {
        return roomindex;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Operation getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return sender+"向"+receiver+"发送消息："+operation.toString();
    }
}