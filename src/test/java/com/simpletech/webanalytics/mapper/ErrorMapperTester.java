package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Before;
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
public class ErrorMapperTester {

	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");
	@Autowired
	ErrorMapper mapper;

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

	@Test
	public void brand() throws Exception {

		Object result = mapper.brand(100, 0);//查询出ua,ip,brand
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));

	}

	@Test
	public void model() throws Exception {
		Object result = mapper.model(100, 0);
		System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
	}

	@Test
	public void browser() throws Exception{
		Object result = mapper.browser(100, 0);
		System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
	}

	@Test
	public void city()throws Exception{
		Object result = mapper.city(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void nettype() throws Exception {
		Object result = mapper.nettype(100, 0);
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void depth() throws Exception {
		Object result = mapper.depth(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void country() throws Exception {
		Object result = mapper.country(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void appname() throws Exception {
		Object result = mapper.appname(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void province() throws Exception {
		Object result = mapper.province(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void lang() throws Exception {
		Object result = mapper.lang(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void system() throws Exception {
		Object result = mapper.system(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void resolution() throws Exception {
		Object result = mapper.resolution(100, 0);
		System.out.println(JacksonUtil.toJson(result));
	}
}
