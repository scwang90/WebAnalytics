package com.simpletech.webanalytics.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 页面排行统计值
 * Created by Administrator on 2015/9/29.
 */
public class PageValue {

    @JsonIgnore
    private String pid;
    private String name;
    private Integer pv;
    private Integer up;
    private Integer ts;
    private Integer uv;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Integer getUp() {
        return up;
    }

    public void setUp(Integer up) {
        this.up = up;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }
}
