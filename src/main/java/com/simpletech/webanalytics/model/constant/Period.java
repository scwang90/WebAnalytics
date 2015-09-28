package com.simpletech.webanalytics.model.constant;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * ÖÜÆÚ
 * Created by Administrator on 2015/9/25.
 */
public enum Period {

    hour("yyMMddHH", Calendar.HOUR_OF_DAY),
    day("yyMMdd", Calendar.DAY_OF_MONTH),
    week("yy-ww", Calendar.WEEK_OF_YEAR),
    month("yyMM", Calendar.MONTH),
    ;
    private final int field;
    private final SimpleDateFormat format;

    Period(String format,int field){
        this.format = new SimpleDateFormat(format);
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public SimpleDateFormat getFormat() {
        return format;
    }
}
