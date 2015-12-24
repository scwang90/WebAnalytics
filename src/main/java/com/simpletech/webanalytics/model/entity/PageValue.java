package com.simpletech.webanalytics.model.entity;

/**
 * 页面排行统计值
 * Created by 树朾 on 2015/9/29.
 */
public class PageValue {

//    @JsonIgnore
    private String id;             //标题数据库ID（用于查询点击页面详情）
    private String url;            //页面链接
    private String title;          //页面标题

    private int uv;                //独立用户数
    private int vt;                //visit 数
    private int pv;                //pv 数
    private float ruv;             //独立用户 占比
    private float rvt;             //visit 占比
    private float rpv;             //pv 占比

    private int load;              //平均加载时间 毫秒
    private int spent;             //平均加载时间 毫秒

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public float getRvt() {
        return rvt;
    }

    public void setRvt(float rvt) {
        this.rvt = rvt;
    }

    public float getRpv() {
        return rpv;
    }

    public void setRpv(float rpv) {
        this.rpv = rpv;
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
