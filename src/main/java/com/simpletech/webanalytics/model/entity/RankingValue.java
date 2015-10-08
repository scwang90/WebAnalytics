package com.simpletech.webanalytics.model.entity;

/**
 * 排行统计值
 * Created by Administrator on 2015/9/29.
 */
public class RankingValue {

    private String name;        //排行名称
    private String remark;      //排行备注
    private int uv;             //独立用户数
    private int visit;          //visit 数
    private int pv;             //pv 数
    private int ip;             //pv 数
    private float ruv;          //独立用户 占比
    private float rvisit;       //visit 占比
    private float rpv;          //pv 占比
    private float rip;          //ip 占比

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public float getRip() {
        return rip;
    }

    public void setRip(float rip) {
        this.rip = rip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getVisit() {
        return visit;
    }

    public void setVisit(int visit) {
        this.visit = visit;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public float getRuv() {
        return ruv;
    }

    public void setRuv(float ruv) {
        this.ruv = ruv;
    }

    public float getRvisit() {
        return rvisit;
    }

    public void setRvisit(float rvisit) {
        this.rvisit = rvisit;
    }

    public float getRpv() {
        return rpv;
    }

    public void setRpv(float rpv) {
        this.rpv = rpv;
    }
}
