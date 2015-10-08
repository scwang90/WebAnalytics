package com.simpletech.webanalytics.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.util.JacksonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 数据库表t_visit的Mapper层测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class VisitMapperTester {

	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");

	@Autowired
	VisitMapper mapper;

	@Test
	public void format() throws Exception {
		List<Visit> result = mapper.findByPropertyName("","browser_name","IE");
		for (Visit visit : result) {
			visit.setBrowserName("IE9");
			mapper.update(visit);
		}
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void insert() throws Exception{
		Visit model = new Visit();
		Object result = mapper.insert(model);
		System.out.println(JacksonUtil.toJson(result));
	}
	
	@Test
	public void update() throws Exception {
		Visit model = new Visit();
		Object result = mapper.update(model);
		System.out.println(JacksonUtil.toJson(result));
	}
	
	@Test
	public void delete() throws Exception {
		Object result = mapper.delete("1");
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void countAll() throws Exception {
		Object result = mapper.countAll();
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void findAll() throws Exception {
		Object result = mapper.findAll("");
		System.out.println(JacksonUtil.toJson(result));
	}

}
