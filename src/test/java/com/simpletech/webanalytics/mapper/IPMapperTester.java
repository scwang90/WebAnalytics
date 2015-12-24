package com.simpletech.webanalytics.mapper;

import com.ipmapping.cnIP.IPCalculate;
import com.ipmapping.cnIP.RandomIP;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.Date;


/**
 * 数据库表t_visit的Mapper层测试类
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class IPMapperTester {


    @Autowired
    IPMapper mapper;

//    @Before
//    public void setUp() {
//        LoggingAspect.log = false;
//    }

    /**
     * 对比百度接口和从t_ip中查询出的结果
     * @throws Exception
     */
    @Test
    public void find() throws Exception {

        Object result = mapper.find(607757494l);
        System.out.println(JacksonUtil.toJson(result).replaceAll(",\\{","\n{"));
    }

}
