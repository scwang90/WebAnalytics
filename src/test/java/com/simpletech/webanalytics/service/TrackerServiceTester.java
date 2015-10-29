package com.simpletech.webanalytics.service;


import com.simpletech.webanalytics.model.entity.JsEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * TrackerService 测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class TrackerServiceTester {

    @Autowired
    TrackerService service;

    @Test
    public void event() throws Exception{
        JsEvent event = new JsEvent();
        event.setEc("ec");
        event.setEa("ea");
        event.setIdts(new Date().getTime());
        event.setUrl("http://localhost:8080/index");
        event.setTitle("探针测试标题");
        event.setIdvtor("idvtor");
        event.setIdvc(1);
        event.setRefer("");
        event.check();
        service.trackEvent(2, event);
    }

}
