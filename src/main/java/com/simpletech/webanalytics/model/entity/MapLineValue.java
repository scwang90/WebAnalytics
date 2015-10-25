package com.simpletech.webanalytics.model.entity;

/**
 * 分享传播图 线条
 * Created by 树朾 on 2015/9/29.
 */
public class MapLineValue {

    private String sId;     //起始点ID
    private String eId;     //结束点ID
    private Long sts;       //持续时间

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public String geteId() {
        return eId;
    }

    public void seteId(String eId) {
        this.eId = eId;
    }

    public Long getSts() {
        return sts;
    }

    public void setSts(Long sts) {
        this.sts = sts;
    }
}
