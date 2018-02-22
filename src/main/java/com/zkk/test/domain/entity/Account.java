package com.zkk.test.domain.entity;

/**
 * Created by zhangkaikai on 2018/2/11 011 11:20 .
 */
public class Account {
    private int id;
    private String name;
    private String password;


    public Account(String name) {
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
