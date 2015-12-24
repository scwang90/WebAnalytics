package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.dao.ErrorDao;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;

/**
 * 数据库表t_visit的dao层测试类
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class ErrorDaoTester {

	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");
	@Autowired
	ErrorDao dao;

	@Test
	public void brand() {

		Object result = dao.brand(100, 0);//查询出ua,ip,brand
		System.out.println(JacksonUtil.toJson(result));

	}

	@Test
	public void model() {
		Object result = dao.model(100, 0);
		System.out.println(JacksonUtil.toJson(result).replaceAll(",\\{}","\n{"));
	}

	@Test
	public void browser(){
		Object result = dao.browser(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void city()throws Exception{
		Object result = dao.city(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void nettype() {
		Object result = dao.nettype(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void depth() {
		Object result = dao.depth(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void country() {
		Object result = dao.country(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void appname() {
		Object result = dao.appname(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void province() {
		Object result = dao.province(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void lang() {
		Object result = dao.lang(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void system() {
		Object result = dao.system(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void resolution() {
		Object result = dao.resolution(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}
}
