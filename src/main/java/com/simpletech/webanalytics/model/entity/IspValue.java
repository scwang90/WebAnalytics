package com.simpletech.webanalytics.model.entity;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/11/27 16:12.
 */
public class IspValue {
    private String name;    //运营商名称
    private int num;        //数量
    private float rate;     //占比

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
}
