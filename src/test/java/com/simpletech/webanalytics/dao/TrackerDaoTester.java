package com.simpletech.webanalytics.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 探针dao层测试类
 * Created by Administrator on 2015/11/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class TrackerDaoTester {

    @Autowired
    TrackerDao dao;

    @Test
    public void updateVisitEvent() {
        int row = dao.updateVisitEvent(1, "", "");
        System.out.println(row);
    }
}
