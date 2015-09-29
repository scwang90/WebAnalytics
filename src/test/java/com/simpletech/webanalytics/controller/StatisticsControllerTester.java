package com.simpletech.webanalytics.controller;


import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.mapper.VisitMapper;
import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * StatisticsController ������
 * @author ���b
 * @date 2015-09-21 17:03:53 �й���׼ʱ��
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsControllerTester {

    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    StatisticsController controller;

    @Before
    public void setUp(){
        LoggingAspect.log = true;
    }

    @Test
    public void visit() throws Exception{
        for (Norm norm:Norm.values()){
            for (Period period:Period.values()){
//                Object result = controller.norm(norm, "1", period, monthf.parse("2015-9-0"), monthf.parse("2015-9-30"));
//                System.out.println("" + norm + "-" + period);
//                System.out.println(JacksonUtil.toJson(result));
            }
        }
    }

    @Test
    public void pagetitle() throws Exception{
        Object result = controller.pagetitle("1", 0, Period.month, 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void pageurl() throws Exception{
        Object result = controller.pageurl("1", 0, Period.month, 100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }


}
