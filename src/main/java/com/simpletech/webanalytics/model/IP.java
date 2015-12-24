package com.simpletech.webanalytics.model;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/12/10 18:13.
 */
public class IP {
    private long ID;
    private String sIP;
    private String eIP;
    private String province;
    private String city;
    private String isp;

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getsIP() {
        return sIP;
    }

    public void setsIP(String sIP) {
        this.sIP = sIP;
    }

    public String geteIP() {
        return eIP;
    }

    public void seteIP(String eIP) {
        this.eIP = eIP;
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

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
