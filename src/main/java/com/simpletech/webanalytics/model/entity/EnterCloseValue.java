package com.simpletech.webanalytics.model.entity;

/**
 * [入口|出口]页统计值
 * Created by Administrator on 2015/10/27.
 */
public class EnterCloseValue {

    private String url;     //页面链接
    private String title;   //页面标题
    private int pv;         //页面刷新数
    private int uv;         //用户量
    private int vt;         //访问次数
    private int ip;         //ip个数
    private int load;       //平均加载时间
    private int spent;      //平均持续时间

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getVt() {
        return vt;
    }

    public void setVt(int vt) {
        this.vt = vt;
    }

    public int getIp() {
        return ip;
    }

    public void setIp(int ip) {
        this.ip = ip;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getSpent() {
        return spent;
    }

    public void setSpent(int spent) {
        this.spent = spent;
    }
}
