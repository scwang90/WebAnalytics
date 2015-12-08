package com.simpletech.webanalytics.model.entity;

/**
 * 访问统计数据
 * Created by 树朾 on 2015/9/25.
 */
public class VisitPageViewMapValue extends MapValue{

    private int vt;             //visit 数
    private int pv;             //pv 数
    private int uv;             //独立用户数
    private int ip;             //pv 数

    public VisitPageViewMapValue() {
    }

    public VisitPageViewMapValue(float min, float max, String unit) {
        super(min, max, unit);
    }

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

    @Override
    public boolean isEmpty() {
        return vt == 0 && pv == 0 && uv == 0 && ip == 0;
    }
}
