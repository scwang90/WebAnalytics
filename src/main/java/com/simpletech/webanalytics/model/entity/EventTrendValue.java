package com.simpletech.webanalytics.model.entity;

/**
 * 事件统计值
 * Created by 树朾 on 2015/9/29.
 */
public class EventTrendValue extends TrendValue {

    private int num;
    private int user;
    private float rnum;
    private float ruser;

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getRnum() {
        return rnum;
    }

    public void setRnum(float rnum) {
        this.rnum = rnum;
    }

    public float getRuser() {
        return ruser;
    }

    public void setRuser(float ruser) {
        this.ruser = ruser;
    }
}
