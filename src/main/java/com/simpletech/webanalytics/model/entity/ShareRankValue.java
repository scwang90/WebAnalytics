package com.simpletech.webanalytics.model.entity;

/**
 * 页面排行
 * Created by 树朾 on 2015/9/29.
 */
public class ShareRankValue {

    private String id;      //页面ID
    private Integer num;    //数量
    private String url;     //链接
    private String title;   //链接

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
