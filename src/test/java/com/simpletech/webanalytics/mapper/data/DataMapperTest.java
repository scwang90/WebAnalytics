package com.simpletech.webanalytics.mapper.data;

import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * 异常数据的Mapper层测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class DataMapperTest {

    @Autowired
    DataMapper mapper;

    @Test
    public void testAbnormalNewUserVisit() throws Exception {
        Object result = mapper.abnormalNewUserVisit(100,0);
        System.out.println(JacksonUtil.toJson(result));
    }
}