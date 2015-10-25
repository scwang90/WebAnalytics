package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * 数据库表t_visit的Mapper层测试类
 *
 * @author 树朾
 * @date 2015-010-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsMapperTester {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    StatisticsMapper mapper;

    @Test
    public void nettype() throws Exception {
        Object result = mapper.nettype("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void browser() throws Exception {
        Object result = mapper.browser("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void system() throws Exception {
        Object result = mapper.system("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void model() throws Exception {
        Object result = mapper.model("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void brand() throws Exception {
        Object result = mapper.brand("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void ip() throws Exception {
        Object result = mapper.ip("0", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void coutRanking() throws Exception {
        Object result = mapper.coutRanking("1", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void eventDay() throws Exception {
        Object result = mapper.eventDay("1", "ec", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void cvisit() throws Exception {
        Object result = mapper.countVisit("1", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void cuser() throws Exception {
        Object result = mapper.countUsers("1", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void event() throws Exception {
        Object result = mapper.event("1", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void visit() throws Exception {
        Object result = mapper.visitDay("1", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void pageurl() throws Exception {
        Object result = mapper.pageurl("0", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void pageBrowser() throws Exception {
        Object result = mapper.pageBrowser("0", "87df6e16-2f22-437f-93af-eb66ec5bf605", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

}
