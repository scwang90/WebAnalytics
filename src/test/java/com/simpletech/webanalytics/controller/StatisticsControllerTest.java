package com.simpletech.webanalytics.controller;

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

import static org.junit.Assert.*;

/**
 * StatisticsController 测试类
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsControllerTest {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    StatisticsController controller;

    @Before
    public void setUp() {
        LoggingAspect.log = false;
    }

    @Test
    public void testVisitorSpan() throws Exception {
        Object result = controller.visitorSpan(1, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitorTrend() throws Exception {
        Object result = controller.visitorTrend(1, Period.hour, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareRank() throws Exception {
        Object result = controller.shareRank(1, "", 10, 0, -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareMap() throws Exception {
        Object result = controller.shareMap(1, "", "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEnterexit() throws Exception {
        Object result = controller.enterexit(1, EnterExit.entry, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testTitleurl() throws Exception {
        Object result = controller.titleurl(1, PageRank.title, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testPageRank() throws Exception {
        Object result = controller.pageRank(1, "", Ranking.brand, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testRanking() throws Exception {
        Object result = controller.ranking(1, Ranking.brand, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventName() throws Exception {

    }

    @Test
    public void testEventNameSpan() throws Exception {
        Object result = controller.eventNameSpan(1, "ec", "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventNameTrend() throws Exception {
        Object result = controller.eventNameTrend(1, "ec", Period.hour, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventRank() throws Exception {
        Object result = controller.eventRank(1, 10, 0, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventTrend() throws Exception {
        Object result = controller.eventTrend(1, Period.hour, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventSpan() throws Exception {
        Object result = controller.eventSpan(1, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitTrend() throws Exception {
        Object result = controller.visitTrend(1, Period.hour, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitSpan() throws Exception {
        Object result = controller.visitSpan(1, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }
}