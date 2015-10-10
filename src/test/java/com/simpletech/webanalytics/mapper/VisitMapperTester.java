package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.aspect.LoggingAspect;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.util.JacksonUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

	@Test
	public void formatsystem() throws Exception {
		HashMap<String,Object> map = new HashMap<>();
		List<Visit> result = mapper.findByPropertyName("", "operate_system", "");
		for (Visit visit : result) {
			Object o = map.get(visit.getIdvisitor());
			map.put(visit.getIdvisitor(), Integer.valueOf(o == null ? 0 : (int) o) + visit.getCountVisits());
		}
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		result = mapper.findByPropertyName("","idvisitor","7c7e14e16e693975");
		for (Visit visit : result) {
			visit.setOperateSystem("WDP");
//			mapper.update(visit);
		}
	}

	@Test
	public void formatnettype() throws Exception {
		HashMap<String,Object> map = new HashMap<>();
//		List<Visit> result = mapper.findByPropertyName("","app_name","null");
		List<Visit> result = mapper.findWhere("", "WHERE net_type is null");
		for (Visit visit : result) {
			Object o = map.get(visit.getIdvisitor());
			map.put(visit.getIdvisitor(), Integer.valueOf(o == null ? 0 : (int) o) + visit.getCountVisits());
		}
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		result = mapper.findByPropertyName("","idvisitor","idvtor");
		for (Visit visit : result) {
			visit.setNetType("BB");
//			mapper.update(visit);
		}
	}

	@Test
	public void formatappname() throws Exception {
		HashMap<String,Object> map = new HashMap<>();
//		List<Visit> result = mapper.findByPropertyName("","app_name","null");
		List<Visit> result = mapper.findWhere("","WHERE app_name is null");
		for (Visit visit : result) {
			Object o = map.get(visit.getIdvisitor());
			map.put(visit.getIdvisitor(), Integer.valueOf(o == null ? 0 : (int) o) + visit.getCountVisits());
		}
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		result = mapper.findByPropertyName("","idvisitor","1ef9eed300297a83");
		for (Visit visit : result) {
			visit.setAppName("WB");
//			mapper.update(visit);
		}
	}

	@Test
	public void formatmodel() throws Exception {
		HashMap<String,Object> map = new HashMap<>();
		List<Visit> result = mapper.findByPropertyName("", "end_model", "");
		for (Visit visit : result) {
			Object o = map.get(visit.getIdvisitor());
			map.put(visit.getIdvisitor(), Integer.valueOf(o == null ? 0 : (int) o) + visit.getCountVisits());
		}
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		result = mapper.findByPropertyName("","idvisitor","323d9301e5531ba8");
		for (Visit visit : result) {
			visit.setEndBrand("XM");
			visit.setEndModel("MI 3");
			mapper.update(visit);
		}
//		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void formatbrand() throws Exception {
		HashMap<String,Object> map = new HashMap<>();
		List<Visit> result = mapper.findByPropertyName("","end_brand","null");
		for (Visit visit : result) {
			Object o = map.get(visit.getIdvisitor());
			map.put(visit.getIdvisitor(), Integer.valueOf(o == null ? 0 : (int) o) + visit.getCountVisits());
		}
		for (Map.Entry<String,Object> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "-" + entry.getValue());
		}
		result = mapper.findByPropertyName("","idvisitor","dc55aeec3f034b01");
		for (Visit visit : result) {
			visit.setEndBrand("SM");
			visit.setEndModel("SM705");
//			mapper.update(visit);
		}
//		System.out.println(JacksonUtil.toJson(result));
	}

	@Test
	public void formatbrowser() throws Exception {
		List<Visit> result = mapper.findByPropertyName("","browser_name","IE");
		for (Visit visit : result) {
			visit.setBrowserName("IE9");
//			mapper.update(visit);
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
