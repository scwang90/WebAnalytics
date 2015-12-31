package com.simpletech.webanalytics.model.entity;

/**
 * 访问时长-分布
 * Created by Administrator on 2015/11/3.
 */
public class DurationMapValue extends MapValue {

    private int pv;         //页面刷新数
    private int uv;         //用户量
    private int vt;         //访问次数
    private int ip;         //ip个数
    private float rpv;      //占比 页面刷新数
    private float ruv;      //占比 用户量
    private float rvt;      //占比 访问次数
    private float rip;      //占比 ip个数

    public DurationMapValue() {
        super();
    }

    public DurationMapValue(float min, float max, String unit) {
        super(min, max, unit);
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

    public float getRvt() {
        return rvt;
    }

    public void setRvt(float rvt) {
        this.rvt = rvt;
    }

    public float getRip() {
        return rip;
    }

    public void setRip(float rip) {
        this.rip = rip;
    }

    @Override
    public boolean isEmpty() {
        return vt == 0 && pv == 0 && uv == 0 && ip == 0;
    }
}
