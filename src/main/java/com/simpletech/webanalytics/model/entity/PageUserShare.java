package com.simpletech.webanalytics.model.entity;

/**
 * 个人分享统计值
 * Created by 树朾 on 2015/9/30.
 */
public class PageUserShare {

    private int uv;         //带来用户数量 Unique Visitor
    private int pv;         //带来PV数  Page View
    private int sh;         //被分享的数量  shared

    /**
     * 微信用户openid
     */
    private String openid;
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

    public int getUv() {
        return uv;
    }

    public void setUv(int uv) {
        this.uv = uv;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getSh() {
        return sh;
    }

    public void setSh(int sh) {
        this.sh = sh;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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
