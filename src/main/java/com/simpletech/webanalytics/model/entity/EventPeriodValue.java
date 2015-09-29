package com.simpletech.webanalytics.model.entity;

import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.util.AfStringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 事件统计值
 * Created by Administrator on 2015/9/29.
 */
public class EventPeriodValue {

    private static final SimpleDateFormat format = new SimpleDateFormat();

    private Date time;
    private String date;
    private Long num;
    private Float rn;
    private Integer user;
    private Float ru;

    public Float getRn() {
        return rn;
    }

    public void setRn(Float rn) {
        this.rn = rn;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Float getRu() {
        return ru;
    }

    public void setRu(Float ru) {
        this.ru = ru;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
        bindTime(date);
    }

    private void bindTime(String date) {
        try {
            if (AfStringUtil.isNotEmpty(date)){
                for (String period: Period.PERIODS){
                    if (date.length() == period.length()){
                        format.applyPattern(period);
                        time = format.parse(date);
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
