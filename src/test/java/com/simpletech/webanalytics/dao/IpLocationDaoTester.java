package com.simpletech.webanalytics.dao;

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
public class IpLocationDaoTester {

	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");
	@Autowired
	IpLocationDao dao;

	@Test
	public void findAllIp() throws Exception {

		Object result = dao.findAll();//查询出ua,ip,brand
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

}
