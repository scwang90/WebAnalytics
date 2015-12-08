package com.simpletech.webanalytics.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.util.JacksonUtil;

import javax.xml.ws.Service;

/**
 * 数据库表t_visit的Service层测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class VisitServiceTester {

	@Autowired
	VisitService service;
	
	@Test
	public void insert() throws Exception{
		Visit model = new Visit();
		Object result = service.insert(model);
		System.out.println(JacksonUtil.toJson(result));
	}
	
	@Test
	public void update() throws Exception {
		Visit model = new Visit();
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
		Object result = service.findByPage(5, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

//	@Test
//	public void findWhereByPage() throws Exception {
//		Object result = service.findWhereByPage("where location_compared is null group by location_ip order by visit_servertime", 5, 0);
//		System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
//	}
}
