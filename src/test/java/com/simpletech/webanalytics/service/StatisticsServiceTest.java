package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.model.constant.*;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * 统计服务层测试类
 * Created by Administrator on 2015/11/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsServiceTest {

    @Autowired
    StatisticsService service;

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Before
    public void setUp() throws Exception {
        LoggingAspect.log = false;
    }

    @Test
    public void testVisitTrend() throws Exception {
        Object result = service.visitTrend("1", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitSpan() throws Exception {
        Object result = service.visitSpan("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventSpan() throws Exception {
        Object result = service.eventSpan("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventRank() throws Exception {
        Object result = service.eventRank("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 10, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventTrend() throws Exception {
        Object result = service.eventTrend("1", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventNameTrend() throws Exception {
        Object result = service.eventNameTrend("1", "ec", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 10, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventNameSpan() throws Exception {
        Object result = service.eventNameSpan("1", "ec", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testTitleurl() throws Exception {
        Object result = service.titleurlRank("1", PageRank.title, RankingType.pv, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 10, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitorSpan() throws Exception {
        Object result = service.visitorSpan("1", monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitorTrend() throws Exception {
        Object result = service.visitorTrend("1", Period.day, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testRanking() throws Exception {
        Object result = service.siteRank("1", Ranking.brand, RankingType.uv, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 10, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testPageRank() throws Exception {
        Object result = service.pageRank("1", "1", Ranking.brand, RankingType.uv, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 10, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareMap() throws Exception {
        Object result = service.shareMap("1", "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", monthf.parse("2015-12-0"), monthf.parse("2015-12-30"));
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareRank() throws Exception {
        Object result = service.shareRank("1", monthf.parse("2015-12-0"), monthf.parse("2015-12-30"), 10, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEnterexit() throws Exception {
        Object result = service.enterexitRank("1", EnterExit.exit, RankingType.pv, monthf.parse("2015-11-0"), monthf.parse("2015-11-30"), 10, 0);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }
}