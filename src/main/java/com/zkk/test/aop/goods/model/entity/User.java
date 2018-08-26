package com.zkk.test.aop.goods.model.entity;

/**
 * Created by zkk on 2018/8/26 12:17 .
 */
public class User {
    private int uid;
    private String uname;
    private String Level; //用户的级别

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }
}
