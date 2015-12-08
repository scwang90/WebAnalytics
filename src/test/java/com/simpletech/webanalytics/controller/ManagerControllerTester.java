package com.simpletech.webanalytics.controller;

import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 管理API Controller 测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class ManagerControllerTester {

    @Autowired
    ManagerController managerController;

    @Test
    public void sitelist() {
        Object result = managerController.siteList("",100, 0);
        System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void siteadd() {
        Site model = new Site();
        Object result = managerController.siteAdd("",model);
        System.out.println(JacksonUtil.toJson(result));
    }

}
