package com.simpletech.webanalytics.model.entity;

/**
 * [入口|出口]页统计值
 * Created by Administrator on 2015/10/27.
 */
public class EnterCloseValue {

    private String name;    //页面
    private int pv;         //页面刷新数
    private int uv;         //用户量
    private int vt;         //访问次数
    private int ip;         //ip个数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }
}
