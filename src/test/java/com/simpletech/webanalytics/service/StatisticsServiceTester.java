package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.UUID;

/**
 * 数据库表t_site的Service层测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class StatisticsServiceTester {

	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");
	@Autowired
	IspService service;
	
	@Test
	public void isp() throws Exception{

		Object result = service.isp("1", monthf.parse("2015-10-0"), monthf.parse("2015-11-30"));
		System.out.println(JacksonUtil.toJson(result));
	}

}
