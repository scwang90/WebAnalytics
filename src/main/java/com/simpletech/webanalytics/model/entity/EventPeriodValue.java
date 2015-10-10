package com.simpletech.webanalytics.model.entity;

import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.util.AfStringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 事件统计值
 * Created by 树朾 on 2015/9/29.
 */
public class EventPeriodValue extends PeriodValue{

    private int num;
    private float rn;
    private int user;
    private float ru;

    public float getRn() {
        return rn;
    }

    public void setRn(float rn) {
        this.rn = rn;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public float getRu() {
        return ru;
    }

    public void setRu(float ru) {
        this.ru = ru;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

}
