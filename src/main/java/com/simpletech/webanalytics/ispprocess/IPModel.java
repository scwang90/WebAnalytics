package com.simpletech.webanalytics.ispprocess;

/**
 * main.java
 * Created by ChenQi on 2015/12/28 9:58.
 */
public class IPModel {
    private String sIP;//开始IP
    private String eIP;//结束IP
    private long sIP_num;//开始IP对应的长整数
    private long eIP_num;//结束IP对应的长整数
    private String isp;//运营商

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

    public long getsIP_num() {
        return sIP_num;
    }

    public void setsIP_num(long sIP_num) {
        this.sIP_num = sIP_num;
    }

    public long geteIP_num() {
        return eIP_num;
    }

    public void seteIP_num(long eIP_num) {
        this.eIP_num = eIP_num;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
