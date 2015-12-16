package com.simpletech.webanalytics.model.entity;

/**
 * 分享性别排行值
 * Created by Administrator on 2015/12/14.
 */
public class ShareSexRankValue {

    private String name;    //名称
    private int sex;        //枚举值
    private int pv;         //分享次数
    private int uv;         //分享用户
    private float rpv;      //分享次数 占比
    private float ruv;      //分享用户 占比

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public float getRpv() {
        return rpv;
    }

    public void setRpv(float rpv) {
        this.rpv = rpv;
    }

    public float getRuv() {
        return ruv;
    }

    public void setRuv(float ruv) {
        this.ruv = ruv;
    }
}
