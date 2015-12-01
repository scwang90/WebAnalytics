package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.simpletech.webanalytics.util.JacksonUtil;
import com.webanalytics.useragent.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * 数据库表t_visit的Mapper层测试类
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class IspMapperTester {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    IspMapper mapper;

    @Before
    public void setUp() {
        LoggingAspect.log = false;
    }

    @Test
    public void findWhereIsp() throws Exception {
        Object result = mapper.findWhereIsp("where location_isp is null");
        System.out.println(JacksonUtil.toJson(result));
    }

}
