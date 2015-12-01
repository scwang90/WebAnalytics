package com.simpletech.webanalytics.model.entity;

/**
 * 事件详细-趋势
 * Created by 树朾 on 2015/9/29.
 */
public class EventNameTrendValue extends TrendValue {

    private int num;
    private int user;
    private float rn;
    private float ru;

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
