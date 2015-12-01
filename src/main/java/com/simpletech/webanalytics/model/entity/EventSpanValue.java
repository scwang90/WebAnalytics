package com.simpletech.webanalytics.model.entity;

/**
 * 事件统计-时段
 * Created by 树朾 on 2015/9/29.
 */
public class EventSpanValue {

    private long num;       //触发次数
    private int user;       //触发用户
    private float rnum;     //触发率 -触发次数/Visit次数
    private float ruser;    //参与度 -触发用户/总用户

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
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
