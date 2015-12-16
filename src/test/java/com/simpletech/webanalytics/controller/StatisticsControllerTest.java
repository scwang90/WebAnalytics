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
        Object result = controller.visitorSpan(5, "", 0, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitorTrend() {
        Object result = controller.visitorTrend(5, Period.day, "", 0, Period.week, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareRank() {
        Object result = controller.shareRank(1, "", 10, 0, 0, Period.week, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareMap() {
        Object result = controller.shareMap(1, "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", "", 0, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareToRank() {
        Object result = controller.shareToRank(1, "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", RankingType.pv, "", 0, Period.week, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareSexRank() {
        Object result = controller.shareSexRank(1, "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", RankingType.pv, "", 0, Period.week, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testSiteSexRank() {
        Object result = controller.siteSexRank(1, RankingType.pv, "", 0, Period.week, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEnterexit() {
        Object result = controller.enterexitRank(1, EnterExit.entry, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testTitleurl() {
        Object result = controller.titleurlRank(1, PageRank.url, RankingType.pv, 3, 0, "", 0, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testPageRank() {
        Object result = controller.pageRank(1, "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", Ranking.system, RankingType.ip, 10, 0, "", 0, Period.week, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testRanking() {
        Object result = controller.siteRank(1, Ranking.city, RankingType.pv, 10, 0, "", 0, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

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
    public void testShareRankCount() throws Exception {
        Object result = controller.shareRankCount(1, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEnterexitRankCount() throws Exception {
        Object result = controller.enterexitRankCount(1, EnterExit.entry, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testTitleurlRankCount() throws Exception {
        Object result = controller.titleurlRankCount(1, PageRank.title, "", 0, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testPageRankCount() throws Exception {
        Object result = controller.pageRankCount(1, "", Ranking.brand, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testSiteRankCount() throws Exception {
        Object result = controller.siteRankCount(5, Ranking.city, "", 0, Period.week, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEventRankCount() throws Exception {
        Object result = controller.eventRankCount(1, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testShareTrend() throws Exception {
        Object result = controller.shareTrend(1, Period.hour, "", "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testEnterexitRank() throws Exception {
        Object result = controller.enterexitRank(1, EnterExit.entry, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testTitleurlRank() throws Exception {
        Object result = controller.titleurlRank(1, PageRank.url, RankingType.pv, 10, 0, "", 0, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testSiteRank() throws Exception {
        Object result = controller.siteRank(1, Ranking.brand, RankingType.pv, 10, 0, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testPageTrend() throws Exception {
        Object result = controller.pageVisitTrend(1, "", Period.day, "", -1, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testPageVisitSpan() throws Exception {
        Object result = controller.pageVisitSpan(1, "9cc21431-f66b-42ec-93a1-84069654b13b", "", -1, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitTimeMap() throws Exception {
        Object result = controller.visitTimeMap(1, TimeType.local, "", 0, Period.day, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testPageTimeMap() throws Exception {
        Object result = controller.pageTimeMap(1, "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", TimeType.server, "", 0, Period.week, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }

    @Test
    public void testVisitPageMap() throws Exception {
        Object result = controller.visitPageMap(1, VisitPageType.unique, "2,5,6", "", 0, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
    }
}