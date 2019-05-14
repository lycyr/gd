package com.gameserver.gd.entity;

import java.util.Date;

public class Log {
    private Date time;

    private String message;

    public Log(Date time, String message) {
        this.time = time;
        this.message = message;
    }

    public Log() {
        super();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }
}