package com.simpletech.webanalytics;

import com.simpletech.webanalytics.model.constant.Period;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间跳跃测试
 * Created by SCWANG on 2015-09-28.
 */
public class DateSkip {

    private static DateFormat full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void dateskip() throws ParseException {
        int offset = -3;
        Period span = Period.year;
        Period period = Period.week;
        Date start = timeStart(span,offset);
        Date end = timeEnd(span, offset);
        System.out.println("s=>" + full.format(start));
        System.out.println("e=>" + full.format(end));
        fulldata(period,start,end);
    }

    private void fulldata(Period period, Date start, Date end) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        while (calendar.getTime().before(end)) {
            System.out.println(period.getFormat().format(calendar.getTime()));
            calendar.add(period.getField(), 1);
        }
    }

    /**
     * 根据周期和便宜计算开始时间
     *
     * @param span   时间跨度
     * @param offset 偏移
     * @return 开始时间
     */
    private Date timeStart(Period span, int offset) throws ParseException {
        int field = span.getField();
        DateFormat format = span.getFormat();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(format.format(calendar.getTime())));
        calendar.add(field, offset);
        return calendar.getTime();
    }

    /**
     * 根据周期和便宜计算结束时间
     *
     * @param span   时间跨度
     * @param offset 偏移
     * @return 结束时间
     */
    private Date timeEnd(Period span, int offset) throws ParseException {
        int field = span.getField();
        DateFormat format = span.getFormat();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(format.parse(format.format(calendar.getTime())));
        calendar.add(field, offset + 1);
        return calendar.getTime();
    }

}
