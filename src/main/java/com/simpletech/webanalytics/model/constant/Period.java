package com.simpletech.webanalytics.model.constant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 周期
 * Created by Administrator on 2015/9/25.
 */
public enum Period {

    year(null, "yyMM", Calendar.YEAR),
    month(year, "yyMM", Calendar.MONTH),
    week(month, "yy-ww", Calendar.WEEK_OF_YEAR),
    day(week, "yyMMdd", Calendar.DAY_OF_MONTH),
    hour(day, "yyMMddHH", Calendar.HOUR_OF_DAY),
    ;
    private final int field;
    private final int parentfield;
    private final DateFormat format;
    private final DateFormat parentformat;

    Period(Period parent,String format,int field){
        this.format = new SimpleDateFormat(format);
        this.field = field;
        this.parentfield = parent.field;
        this.parentformat = parent.format;
    }

    public int getField() {
        return field;
    }

    public int getParentField() {
        return parentfield;
    }

    public DateFormat getFormat() {
        return format;
    }

    public DateFormat getParentformat() {
        return parentformat;
    }
}
