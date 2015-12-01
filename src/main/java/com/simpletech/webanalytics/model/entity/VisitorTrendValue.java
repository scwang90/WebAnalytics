package com.simpletech.webanalytics.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 访问者数据
 * Created by 树朾 on 2015/9/30.
 */
public class VisitorTrendValue extends TrendValue {

    private int uv;         //独立用户量 Unique Visitor
    private int nv;         //新用户量  New Visitor
    private int ov;         //老用户量  Old Visitor
    private float nr;       //新用户比率 New Ratio
    private float or;       //老用户比率 Old Ratio

    @JsonIgnore
    int subnv;      //子新用户  Sub New Visitor

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getNv() {
        return nv;
    }

    public void setNv(int nv) {
        this.nv = nv;
    }

    public int getOv() {
        return ov;
    }

    public void setOv(int ov) {
        this.ov = ov;
    }

    public float getNr() {
        return nr;
    }

    public void setNr(float nr) {
        this.nr = nr;
    }

    public float getOr() {
        return or;
    }

    public void setOr(float or) {
        this.or = or;
    }

    public int getSubnv() {
        return subnv;
    }

    public void setSubnv(int subnv) {
        this.subnv = subnv;
    }

}
