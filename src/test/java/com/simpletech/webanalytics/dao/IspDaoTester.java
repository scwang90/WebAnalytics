package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 数据库表t_visit的Dao层测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class IspDaoTester {

	@Autowired
	IspDao dao;

	@Test
	public void findWhereIsp() {
		Object result = dao.findWhereIsp("where location_isp is null group by location_ip order by visit_servertime DESC",1000,0);
		System.out.println(JacksonUtil.toJson(result));
	}
	
}
