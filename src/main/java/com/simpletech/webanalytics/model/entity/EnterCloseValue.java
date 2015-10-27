package com.simpletech.webanalytics.model.entity;

/**
 * [入口|出口]页统计值
 * Created by Administrator on 2015/10/27.
 */
public class EnterCloseValue {

    private String name;        //页面
    private String num;         //次数

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
