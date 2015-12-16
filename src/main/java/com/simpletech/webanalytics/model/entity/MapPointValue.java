package com.simpletech.webanalytics.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 分享传播图 点
 * Created by 树朾 on 2015/9/29.
 */
public class MapPointValue {

    private String Id;      //节点ID
    private int cl;         //分类 0 起始点 1 中间点 2 叶子节点
    private int pv;         //本节点的 PV 量
    private int uv;         //本节点的 UV 量
    private int sh;         //被分享数
    private String mk;      //remark 备注
    @JsonIgnore
    public int opv;        //原始PV
    @JsonIgnore
    public int ouv;        //原始UV
    @JsonIgnore
    public boolean isStart;//原始节点类型

    /**
     * 微信用户昵称
     */
    private String name;
    /**
     * 微信用户头像链接
     */
    private String head;
    /**
     * 微信用户性别
     */
    private String sex;
    /**
     * 微信用户省份
     */
    private String province;
    /**
     * 微信用户城市
     */
    private String city;
    /**
     * 微信用户国家
     */
    private String country;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public int getCl() {
        return cl;
    }

    public void setCl(int cl) {
        this.cl = cl;
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

    public int getSh() {
        return sh;
    }

    public void setSh(int sh) {
        this.sh = sh;
    }

    public String getMk() {
        return mk;
    }

    public void setMk(String mk) {
        this.mk = mk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
