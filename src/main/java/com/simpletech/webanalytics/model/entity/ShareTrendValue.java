package com.simpletech.webanalytics.model.entity;

/**
 * 分享趋势
 * Created by 树朾 on 2015/9/30.
 */
public class ShareTrendValue extends TrendValue {

    private int vt;         //分享次数
    private int pv;         //打开次数
    private int uv;         //分享人数

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

}
