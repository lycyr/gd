package com.gameserver.gd.pvp;

import java.util.Date;
import java.util.Map;

public class DuelInfo {
    private Date time;
    private String email;
    private String action;
    private Map<String, Object> paramsMap;
    private String duelId;

    public DuelInfo(Date time, String email, String duelId, String action, Map<String, Object> params) {
        this.time = time;
        this.email = email;
        this.action = action;
        this.paramsMap = params;
        this.duelId = duelId;
    }

    public DuelInfo(String email, String duelId, String action, Map<String, Object> params) {
        this(new Date(), email, duelId, action, params);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Map<String, Object> getParams() {
        return paramsMap;
    }

    public void setParams(Map<String, Object> params) {
        this.paramsMap = params;
    }

    public String getDuelId() {
        return duelId;
    }

    public void setDuelId(String duelId) {
        this.duelId = duelId;
    }
}
