package com.simpletech.webanalytics.controller;


import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.PageRank;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.RankingType;
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
public class StatisticsControllerTester {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    StatisticsController controller;

    @Before
    public void setUp() {
        LoggingAspect.log = true;
    }

    @Test
    public void visit() {
        for (Norm norm : Norm.values()) {
            for (Period period : Period.values()) {
//                Object result = controller.norm(norm, "1", period, monthf.parse("2015-9-0"), monthf.parse("2015-9-30"));
//                System.out.println("" + norm + "-" + period);
//                System.out.println(JacksonUtil.toJson(result));
            }
        }
    }

    @Test
    public void pagetitle() {
        Object result = controller.titleurlRank(1, PageRank.title, RankingType.pv, 100, 0, null, 0, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void pageurl() {
        Object result = controller.titleurlRank(1, PageRank.url, RankingType.pv, 100, 0, null, 0, Period.month, null, null);
        System.out.println(JacksonUtil.toJson(result));
    }


}
