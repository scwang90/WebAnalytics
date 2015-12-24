package com.simpletech.webanalytics.dao;

import com.ipmapping.cnIP.RandomIP;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;


/**
 * 数据库表t_visit的dao层测试类
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class IPDaoTester {

	@Autowired
	IPDao ipDao;

	@Test
	public void find()throws Exception{
        File file=new File("E:\\ipCompare.txt");
        int i=1;
        while(i<=9000){
            RandomIP rdIP=new RandomIP();
            String ip=rdIP.getRandomIp();
//            ipDao.fileWrite(file,ip);
            System.out.println(i);
            i++;
        }

	}
}
