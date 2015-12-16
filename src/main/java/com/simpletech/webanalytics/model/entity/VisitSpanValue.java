package com.simpletech.webanalytics.model.entity;

/**
 * 访问统计数据
 * Created by 树朾 on 2015/9/25.
 */
public class VisitSpanValue {

    private int vt;             //visit 数
    private int pv;             //pv 数
    private int uv;             //独立用户数
    private int ip;             //pv 数
    private int spent;          //平均耗时
    private int exit;           //退出次数
    private float rexit;        //退出率

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
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

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }

    public int getExit() {
        return exit;
    }

    public void setExit(int exit) {
        this.exit = exit;
    }

    public float getRexit() {
        return rexit;
    }

    public void setRexit(float rexit) {
        this.rexit = rexit;
    }
}
