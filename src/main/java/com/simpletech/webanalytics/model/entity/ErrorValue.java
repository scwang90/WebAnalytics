package com.simpletech.webanalytics.model.entity;

/**
 * 排行统计值
 * Created by 树朾 on 2015/9/29.
 */
public class ErrorValue {

    private String ip;          //外网IP
    private String name;        //错误名称:brand、model……
    private String remark;      //备注
    private String uerAgent;    //userAgent

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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

    public String getUerAgent() {
        return uerAgent;
    }

    public void setUerAgent(String uerAgent) {
        this.uerAgent = uerAgent;
    }
}
