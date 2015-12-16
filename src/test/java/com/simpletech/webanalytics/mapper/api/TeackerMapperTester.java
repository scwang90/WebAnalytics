package com.simpletech.webanalytics.mapper.api;

import com.simpletech.webanalytics.mapper.api.TrackerMapper;
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
public class TeackerMapperTester {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    TrackerMapper mapper;

    @Test
    public void updateVisitEvent() throws Exception {
        Object result = mapper.updateVisitEvent("1");
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void getVisitHalfHour() throws Exception {
        Object result = mapper.getVisitHalfHour("1", "idvtor");
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void getUrl() throws Exception {
        Object result = mapper.getUrl(1, "http://localhost:8080/index".hashCode());
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void getTitle() throws Exception {
        Object result = mapper.getTitle(1, "探针测试标题".hashCode());
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void existVisitor() throws Exception {
        Object result = mapper.existVisitor(1, "idvtor");
        System.out.println(JacksonUtil.toJson(result));
    }

}
