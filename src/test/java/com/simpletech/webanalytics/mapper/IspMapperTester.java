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
public class IspMapperTester {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    IspMapper mapper;

//    @Before
//    public void setUp() {
//        LoggingAspect.log = false;
//    }
    @Test
    public void findWhereIsp() throws Exception {
        Object result = mapper.findWhereIsp("where location_isp is null group by location_ip order by visit_servertime DESC",1000,0);
        System.out.println(JacksonUtil.toJson(result));
    }
    @Test
    public void findWhere() throws Exception {
        long c=System.currentTimeMillis();
        Object result = mapper.findWhere("where location_isp is null group by location_ip order by visit_servertime DESC",1000,0);
        System.out.println("查找1000条数据耗时：" + (System.currentTimeMillis() - c) / 1000f + "秒");
//        System.out.println(JacksonUtil.toJson(result));
    }
}
