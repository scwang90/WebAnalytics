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
    public void testVisitorSpan() {
        Object result = controller.visitorSpan(4, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitorTrend() {
        Object result = controller.visitorTrend(1, Period.hour, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareRank() {
        Object result = controller.shareRank(1, "", 10, 0, -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareMap() {
        Object result = controller.shareMap(1, "", "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

//    @Test
//    public void testEnterexit() {
//        Object result = controller.enterexit(1, EnterExit.entry, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
//        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
//    }
//
//    @Test
//    public void testTitleurl() {
//        Object result = controller.titleurl(1, PageRank.title, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
//        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
//    }
//
//    @Test
//    public void testPageRank() {
//        Object result = controller.pageRank(1, "", Ranking.brand, RankingType.ip, 10, 0, "", -1, Period.day, null, null);
//        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
//    }
//
//    @Test
//    public void testRanking() {
//        Object result = controller.ranking(1, Ranking.brand, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
//        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
//    }

    @Test
    public void testEventName() {
    }

    @Test
    public void testEventNameSpan() {
        Object result = controller.eventNameSpan(1, "ec", "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventNameTrend() {
        Object result = controller.eventNameTrend(1, "ec", Period.hour, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventRank() {
        Object result = controller.eventRank(1, 10, 0, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventTrend() {
        Object result = controller.eventTrend(1, Period.hour, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventSpan() {
        Object result = controller.eventSpan(1, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitTrend() {
        Object result = controller.visitTrend(1, Period.hour, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitSpan() {
        Object result = controller.visitSpan(1, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void shareMap() {
        long start = System.currentTimeMillis();
        Object result = controller.shareMap(1, "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", "",0, Period.month, null, null);
        long end = System.currentTimeMillis();
        System.out.println("spent="+(end-start)/1000);
        String json;
        start = System.currentTimeMillis();
        System.out.println(json = JacksonUtil.toJson(result).replace("{", "\n{"));
        end = System.currentTimeMillis();
        System.out.println("spent="+(end-start)/1000);
        System.out.println((json.length()*2)/1000+"kb");
    }

}