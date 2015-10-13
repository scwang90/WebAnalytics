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
 * @date 2015-09-21 17:03:53 中国标准时间
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
    public void coutRanking() throws Exception {
        Object result = mapper.coutranking("1", monthf.parse("2015-9-0"), monthf.parse("2015-9-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void eventDay() throws Exception {
        Object result = mapper.eventDay("1", "ec", monthf.parse("2015-9-0"), monthf.parse("2015-9-30"),100,0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void cvisit() throws Exception {
        Object result = mapper.countVisit("1", monthf.parse("2015-9-0"), monthf.parse("2015-9-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void cuser() throws Exception {
        Object result = mapper.countUsers("1", monthf.parse("2015-9-0"), monthf.parse("2015-9-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void event() throws Exception {
        Object result = mapper.event("1", monthf.parse("2015-9-0"), monthf.parse("2015-9-30"), 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void visit() throws Exception {
        Object result = mapper.visitDay("1", monthf.parse("2015-9-0"), monthf.parse("2015-9-30"));
        System.out.println(JacksonUtil.toJson(result));
    }

}
