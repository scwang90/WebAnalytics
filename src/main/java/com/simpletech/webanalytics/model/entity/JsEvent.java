package com.simpletech.webanalytics.model.entity;

import com.simpletech.webanalytics.annotations.Must;
import com.simpletech.webanalytics.model.Event;

import java.util.Date;

/**
 * Js探针 事件抓取 Brand
 * Created by 树朾 on 2015/9/25.
 */
public class JsEvent extends JsDetect {

    @Must("事件类别")
    private String ec;      //(必需) 事件类别 (如: 视频、音乐、游戏)
    @Must("事件动作")
    private String ea;      //(必需) 事件动作 (如: 播放、暂停、持续时间、添加播放列表,下载,点击……)
    private String en;      //(可选) 事件名称 (如: 电影名字,歌曲名称或文件名称…)
    private Double ev;       //(可选) 事件的值 (必须是一个浮点数或整数值(数字),而不是一个字符串)

    public Event buildEvent(int idsite) {
        Event event = new Event();
        event.setCategory(this.ec);
        event.setAction(this.ea);
        event.setName(this.en);
        event.setValue(this.ev);
        event.setIdsite(idsite);
        event.setIdvisitor(this.getIdvtor());
        event.setLocalTime(new Date(this.getVisits()));
        return event;
    }

    public String getEc() {
        return ec;
    }

    public void setEc(String ec) {
        this.ec = ec;
    }

    public String getEa() {
        return ea;
    }

    public void setEa(String ea) {
        this.ea = ea;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public Double getEv() {
        return ev;
    }

    public void setEv(Double ev) {
        this.ev = ev;
    }
}
