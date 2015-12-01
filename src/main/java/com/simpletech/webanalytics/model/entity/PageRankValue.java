package com.simpletech.webanalytics.model.entity;

/**
 * ҳ������ͳ��ֵ
 * Created by ���b on 2015/9/29.
 */
public class PageRankValue {

    private String pid;     //ҳ��ID
    private Integer num;    //��������
    private String url;     //ҳ������

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
