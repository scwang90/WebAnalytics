package com.simpletech.webanalytics.model.entity;

/**
 * 事件统计值
 * Created by Administrator on 2015/9/29.
 */
public class EventValue {

    private String name;
    private Long num;
    private Float rn;
    private Integer user;
    private Float ru;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getRn() {
        return rn;
    }

    public void setRn(Float rn) {
        this.rn = rn;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Float getRu() {
        return ru;
    }

    public void setRu(Float ru) {
        this.ru = ru;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }


}
