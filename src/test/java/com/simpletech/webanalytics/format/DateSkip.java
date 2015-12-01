package com.simpletech.webanalytics.format;

import com.simpletech.webanalytics.model.constant.Period;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间跳跃测试
 * Created by SCWANG on 2015-09-28.
 */
public class DateSkip {

    private static DateFormat full = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    static Pattern patternPiwikCookie = Pattern.compile("(\\w+)\\.",Pattern.CASE_INSENSITIVE);

    @Test
    public void patternPiwikCookie() throws ParseException {
        Matcher matcher = patternPiwikCookie.matcher("5096af1759bd3e5e.1446019398.1.1446021228.1446019398.");
        if (matcher.find()) {
            System.out.println(matcher.group(1));
        } else {
            System.out.println("fail");
        }
    }

    @Test
    public void datespan() throws ParseException {
        Date now = new Date();
        System.out.println(full.format(now));
        System.out.println(full.format(new Date(now.getTime()-365*24*60*60*1000)));
        System.out.println(full.format(new Date(now.getTime()-365*24*60*60*1000l)));
        System.out.println(full.format(full.parse("2015-11-05 21:31:28")));
        System.out.println(full.format(new Date(full.parse("2015-11-05 21:31:28").getTime()+1553913*1000l)));
    }

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
