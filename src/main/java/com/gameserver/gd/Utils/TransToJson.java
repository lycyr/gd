package com.gameserver.gd.Utils;

public class TransToJson {
    private int code;
    private Object object;

    public TransToJson(int code,Object object){
        this.code = code;
        this.object = object;
    }

    public TransToJson(Object object){
        this.object = object;
        this.code = 0;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Object getObject() {
        return object;
    }
}