package com.simpletech.webanalytics.mapper.api;

import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Before;
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

    @Before
    public void setUp() {
        LoggingAspect.log = false;
    }

    @Test
    public void entryUrls() throws Exception {
        Object result = mapper.entryUrlsRank("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 10, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void exitUrls() throws Exception {
        Object result = mapper.exitUrlsRank("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 10, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void nettype() throws Exception {
        Object result = mapper.rankNettype("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void browser() throws Exception {
        Object result = mapper.rankBrowser("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void system() throws Exception {
        Object result = mapper.rankSystem("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void model() throws Exception {
        Object result = mapper.rankModel("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void brand() throws Exception {
        Object result = mapper.rankBrand("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void ip() throws Exception {
        Object result = mapper.rankIp("0", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void coutRanking() throws Exception {
        Object result = mapper.coutRanking("1", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void eventDay() throws Exception {
        Object result = mapper.eventNameTrendDay("1", "ec", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void eventNameTrendDay() throws Exception {
        Object result = mapper.eventNameTrendDay("1", "ec", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
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
        Object result = mapper.eventRank("1", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void visit() throws Exception {
        Object result = mapper.visitTrendDay("0", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void pageurl() throws Exception {
        Object result = mapper.pageUrlRank("1", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void pagetitle() throws Exception {
        Object result = mapper.pageTitleRank("1", "pv", monthf.parse("2015-12-0"), monthf.parse("2015-12-30"), 3, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
    }

    @Test
    public void pageBrowser() throws Exception {
        Object result = mapper.pageBrowser("0", "87df6e16-2f22-437f-93af-eb66ec5bf605", "pv", monthf.parse("2015-10-0"), monthf.parse("2015-10-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void shareTrendHour() throws Exception {
        Object result = mapper.shareTrendHour("1", "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", monthf.parse("2015-12-0"), monthf.parse("2015-12-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void shareSpan() throws Exception {
        Object result = mapper.shareSpan("1", "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", monthf.parse("2015-12-0"), monthf.parse("2015-12-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void shareToRank() throws Exception {
        Object result = mapper.shareToRank("1", "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", "pv", monthf.parse("2015-12-0"), monthf.parse("2015-12-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

}
