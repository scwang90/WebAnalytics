package com.simpletech.webanalytics.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 页面排行
 * Created by 树朾 on 2015/9/29.
 */
public class ShareToRankValue {

    private String name;    //页面ID
    private int pv;         //点击数量
    private int uv;         //分享用户

    @JsonIgnore
    private int ruv;        //uv中间结果

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
