package com.simpletech.webanalytics.model.entity;

/**
 * 页面排行统计值
 * Created by 树朾 on 2015/9/29.
 */
public class PageRankingValue {

    private String pid;     //页面ID
    private Integer num;    //分享数量
    private String url;     //页面链接

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
