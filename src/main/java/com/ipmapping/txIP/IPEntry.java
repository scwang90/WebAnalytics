package com.ipmapping.txIP;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/10/27 17:41.
 */
public class IPEntry {
    public String beginIp;
    public String endIp;
    public String country;
    public String area;

    /**
     * 构造函数
     */
    public IPEntry() {
        beginIp = endIp = country = area = "";
    }
}
