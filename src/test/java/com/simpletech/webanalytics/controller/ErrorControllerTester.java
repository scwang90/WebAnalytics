package com.simpletech.webanalytics.controller;


import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.model.constant.Norm;
import com.simpletech.webanalytics.model.constant.Period;
import com.simpletech.webanalytics.model.constant.Ranking;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * ErrorController 测试
 *
 * @author
 * @date 2015-09-21 17:03:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class ErrorControllerTester {

//    SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

    @Autowired
    ErrorController controller;

    @Before
    public void setUp() {
        LoggingAspect.log = true;
    }


    @Test
    public void unknownError() {
        Object result = controller.unknownError(Ranking.browser,100,0);
        System.out.println(JacksonUtil.toJson(result));
    }


}
