package com.simpletech.webanalytics.model.entity;

/**
 * 事件统计值
 * Created by 树朾 on 2015/9/29.
 */
public class EventNameValue {

    private String name;
    private long num;
    private float rn;
    private int user;
    private float ru;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRn() {
        return rn;
    }

    public void setRn(float rn) {
        this.rn = rn;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public float getRu() {
        return ru;
    }

    public void setRu(float ru) {
        this.ru = ru;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }


}
