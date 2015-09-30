package com.simpletech.webanalytics.model.entity;

import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.util.AfStringUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PV 统计
 * Created by Administrator on 2015/9/25.
 */
public class PeriodValue {

    private static final SimpleDateFormat format = new SimpleDateFormat();

    private Date time;
    private String date;
    private Long val;

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
        this.bindTime(date);
    }

    public Long getVal() {
        return val;
    }

    public void setVal(Long val) {
        this.val = val;
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
