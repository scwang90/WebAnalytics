package com.simpletech.webanalytics.dao;

import com.simpletech.webanalytics.model.Point;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;


/**
 * 数据库表t_visit的dao层测试类
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class PointDaoTester {

	@Autowired
	PointDao pointDao;

	@Test
	public void findPoint()throws Exception{

        String id="844783ffcb907f1c";
        List<String> find1=find1(id);
        for(int i=0;i<find1.size();i++){
            id=find1.get(i);
            find1(id);
        }
//        System.out.println(JacksonUtil.toJson(results).replace("{","\n{"));
	}

    public List<String> find1(String id){
        List<String> ss=new ArrayList();
        List<Point> results=pointDao.findPoint(id);
        System.out.println(id+"   "+results.size());
        for(Point s:results){
            ss.add(s.getEp());
            System.out.println(s.getEp());
        }
        System.out.println("^^^^^^^^^^^^^^^^^^^^");
        return ss;
    }
}
