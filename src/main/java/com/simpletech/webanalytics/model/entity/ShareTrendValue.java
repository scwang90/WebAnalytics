package com.simpletech.webanalytics.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 分享趋势
 * Created by 树朾 on 2015/9/30.
 */
public class ShareTrendValue extends TrendValue {

    private int vt;         //分享次数
    private int pv;         //打开次数
    private int uv;         //分享人数

    @JsonIgnore
    private int ruv;        //uv中间结果

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
        if (ruv > 0) {
            uv += ruv;
            ruv = 0;
        }
        this.uv = uv;
    }

    public int getRuv() {
        return ruv;
    }

    public void setRuv(int ruv) {
        if (this.uv > 0) {
            this.uv = this.uv + ruv;
        } else {
            this.ruv = ruv;
        }
    }
}
