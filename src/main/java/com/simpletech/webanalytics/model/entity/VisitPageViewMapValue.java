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
//    private float rvt;          //占比 visit 数
//    private float rpv;          //占比 pv 数
//    private float ruv;          //占比 独立用户数
//    private float rip;          //占比 pv 数

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

//    public float getRvt() {
//        return rvt;
//    }
//
//    public void setRvt(float rvt) {
//        this.rvt = rvt;
//    }
//
//    public float getRpv() {
//        return rpv;
//    }
//
//    public void setRpv(float rpv) {
//        this.rpv = rpv;
//    }
//
//    public float getRuv() {
//        return ruv;
//    }
//
//    public void setRuv(float ruv) {
//        this.ruv = ruv;
//    }
//
//    public float getRip() {
//        return rip;
//    }
//
//    public void setRip(float rip) {
//        this.rip = rip;
//    }

    @Override
    public boolean isEmpty() {
        return vt == 0 && pv == 0 && uv == 0 && ip == 0;
    }
}
