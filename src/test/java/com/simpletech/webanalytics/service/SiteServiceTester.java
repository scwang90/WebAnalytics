package com.simpletech.webanalytics.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.util.JacksonUtil;

import java.util.Random;
import java.util.UUID;

/**
 * 数据库表t_site的Service层测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class SiteServiceTester {

	@Autowired
	SiteService service;
	
	@Test
	public void insert() throws Exception{
		Site model = new Site();
		System.out.println(UUID.randomUUID().toString().length());
		//model.setId(new Random().nextInt());
		model.setName("网站分析");
		model.setDomain("www.webanalytics.com");
		Object result = service.insert(model);
		System.out.println(JacksonUtil.toJson(result));
	}
	
	@Test
	public void update() throws Exception {
		Site model = new Site();
		Object result = service.update(model);
		System.out.println(JacksonUtil.toJson(result));
	}
	
	@Test
	public void delete() throws Exception {
		Object result = service.delete("1");
		System.out.println(JacksonUtil.toJson(result));
	}
	
	@Test
	public void countAll() throws Exception {
		Object result = service.countAll();
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void findByPage() throws Exception {
		Object result = service.findByPage(5,0);
		System.out.println(JacksonUtil.toJson(result));
	}

}
