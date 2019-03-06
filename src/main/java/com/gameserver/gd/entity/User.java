package com.gameserver.gd.entity;

import java.util.Date;

public class User {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private Date regtime;

    public User(Integer id, String username, String password, String email, Date regtime) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.regtime = regtime;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }
}