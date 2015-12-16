package com.simpletech.webanalytics.mapper.api;

import com.simpletech.webanalytics.mapper.api.TrackShareMapper;
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
public class TeackerShareMapperTester {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    TrackShareMapper mapper;

//    @Test
//    public void getVisit() throws Exception {
//        Object result = mapper.getVisit(1, "idvtor");
//        System.out.println(JacksonUtil.toJson(result));
//    }

    @Test
    public void getVisitHalfHour() throws Exception {
        Object result = mapper.getShareUser(0, "");
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void checkStartPoint() throws Exception {
        Object result = mapper.checkStartPoint(0, "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", "e101feab9939a450");
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void isStartPoint() throws Exception {
        Object result = mapper.isStartPoint(1, "5f8ddda3-3f4c-4408-b1b1-2768f2d76b17", "e101feab9939a450");
        System.out.println(JacksonUtil.toJson(result));
    }


}
