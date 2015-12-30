package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.useragent.target.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.util.JacksonUtil;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

/**
 * 数据库表t_visit的Mapper层测试类
 *
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
        HashMap<String, Object> map = new HashMap<>();
        List<Visit> result = mapper.findByPropertyName("", "operate_system", "UN");
//        List<Visit> result = mapper.findByPropertyName("", "operate_version", "WDP");
//        List<Visit> result = mapper.findWhere("", "WHERE operate_version like '%;'");
        for (Visit visit : result) {
            String agent = visit.getUseragent();
            OperateSystem.Model system = OperateSystem.parser(agent);
            System.out.println(system.getRemark() + " - " + system.getVersion() + " - " + agent);
//            visit.setOperateSystem(system.getAcronym());
//            visit.setOperateVersion(system.getVersion());
//            mapper.update(visit);
//            Object o = map.get(visit.getIdvisitor());
//            map.put(visit.getIdvisitor(), Integer.valueOf(o == null ? 0 : (int) o) + visit.getCountVisits());
        }
//		for (Map.Entry<String,Object> entry : map.entrySet()) {
//			System.out.println(entry.getKey() + "-" + entry.getValue());
//		}
//		result = mapper.findByPropertyName("","idvisitor","7c7e14e16e693975");
//		for (Visit visit : result) {
//			visit.setOperateSystem("WDP");
////			mapper.update(visit);
//		}
    }

    @Test
    public void formatnettype() throws Exception {
//        List<Visit> result = mapper.findByPropertyName("", "net_type", "G3");
        List<Visit> result = mapper.findWhere("", "WHERE (net_type is null OR net_type='' OR net_type='UN') AND useragent LIKE '%nettype%' ");
        for (Visit visit : result) {
            String agent = visit.getUseragent();
            Nettype.Model nettype = Nettype.parser(agent);
            System.out.println(nettype.getRemark() + " - " + nettype.getValue() + " - " + agent);
            if (!nettype.getNettype().equals(Nettype.Unknown)) {
                visit.setNetType(nettype.getAcronym());
                mapper.update(visit);
            }
        }
//		System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void formatappname() throws Exception {
        HashMap<String, Object> map = new HashMap<>();
//		List<Visit> result = mapper.findByPropertyName("", "app_name", "null");
        List<Visit> result = mapper.findWhere("", "WHERE app_name = 'UN'");
        for (Visit visit : result) {
            String agent = visit.getUseragent();
            Application.Model application = Application.parser(agent);
            System.out.println(application.getRemark() + " - " + application.getVersion() + " - " + agent);
//            visit.setAppName(application.getAcronym());
//            mapper.update(visit);
//            Object o = map.get(visit.getIdvisitor());
//            map.put(visit.getIdvisitor(), Integer.valueOf(o == null ? 0 : (int) o) + visit.getCountVisits());
        }
//        for (Map.Entry<String, Object> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + "-" + entry.getValue());
//        }
//        result = mapper.findByPropertyName("", "idvisitor", "1ef9eed300297a83");
//        for (Visit visit : result) {
//            visit.setAppName("WB");
////			mapper.update(visit);
//        }
    }

    @Test
    public void formatmodel() throws Exception {
//        List<Visit> result = mapper.findByPropertyName("", "end_model", "");
        List<Visit> result = mapper.findWhere("", "WHERE (end_model is null OR end_model='' OR end_model='UN') AND useragent like '%build%'");
        for (Visit visit : result) {
            String agent = visit.getUseragent();
            Brand.Model brand = Brand.parser(agent);
            System.out.println(brand.getRemark() + " - " + brand.getModel() + " - " + agent);
            if (AfStringUtil.isNotEmpty(brand.getModel())) {
                visit.setEndModel(brand.getModel());
                visit.setEndBrand(brand.getAcronym());
                mapper.update(visit);
            }
        }
//		System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void formatbrand() throws Exception {
//        List<Visit> result = mapper.findByPropertyName("", "end_brand", "SS");
        List<Visit> result = mapper.findWhere("", "WHERE end_brand = 'UN'");
        for (Visit visit : result) {
            String agent = visit.getUseragent();
            Brand.Model brand = Brand.parser(agent);
            if (brand.getValue().equals(Brand.Unknown))
                System.err.println(brand.getRemark() + " - " + brand.getModel() + " - " + agent);
            else
                System.out.println(brand.getRemark() + " - " + brand.getModel() + " - " + agent);
            if (AfStringUtil.isNotEmpty(brand.getModel()) || brand.getValue().equals(Brand.Computer)){
                visit.setEndModel(brand.getModel());
                visit.setEndBrand(brand.getAcronym());
                mapper.update(visit);
            }
        }
//		System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void formatbrowser() throws Exception {
//        List<Visit> result = mapper.findByPropertyName("", "browser_name", "UN");
//        List<Visit> result = mapper.findWhere("", "WHERE browser_name is null");
        List<Visit> result = mapper.findWhere("", "WHERE useragent like '%oppo%'");
        for (Visit visit : result) {
            String agent = visit.getUseragent();
            Browser.Model browser = Browser.parser(agent);
            System.out.println(browser.getRemark() + " - " + browser.getVersion() + " - " + agent + "-" + visit.getBrowserVersion());
            visit.setBrowserName(browser.getAcronym());
            visit.setBrowserVersion(browser.getVersion());
            mapper.update(visit);
        }
//		System.out.println(JacksonUtil.toJson(result));
    }

    @Test
    public void insert() throws Exception {
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
