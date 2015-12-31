package com.simpletech.webanalytics.model.entity;

/**
 * 访问统计数据
 * Created by 树朾 on 2015/9/25.
 */
public class VisitPageUniqueMapValue extends MapValue{

    private int vt;             //visit 数 Visit
    private int pv;             //pv 数 Page Visit
    private int uv;             //独立用户数 Unique visitor
    private int up;             //独立页数 Unique Page
//    private float rvt;          //占比 visit 数 Visit
//    private float rpv;          //占比 pv 数 Page Visit
//    private float ruv;          //占比 独立用户数 Unique visitor
//    private float rup;          //占比 独立页数 Unique Page

    public VisitPageUniqueMapValue() {
    }

    public VisitPageUniqueMapValue(float min, float max, String unit) {
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

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
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
//    public float getRup() {
//        return rup;
//    }
//
//    public void setRup(float rup) {
//        this.rup = rup;
//    }

    @Override
    public boolean isEmpty() {
        return vt == 0 && pv == 0 && uv == 0 && up == 0;
    }
}
