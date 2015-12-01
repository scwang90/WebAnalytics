package com.simpletech.webanalytics.model.entity;

/**
 * 事件统计值
 * Created by 树朾 on 2015/9/29.
 */
public class EventTrendValue extends PeriodValue{

    private int num;
    private int user;

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

}
