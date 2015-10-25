package com.simpletech.webanalytics.mapper;

import com.ipmapping.IP;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

/**
 * Ip 的Mapper层测试类
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class IpMapperTester {

    @Autowired
    IpMapper mapper;

    @Before
    public void setUp() {
        LoggingAspect.log = false;
    }

    @Test
    public void group() {
        IP.load("src/main/resources/17monipdb.dat");

        List<HashMap<String, Object>> maps = mapper.findGroupIp();
        for (HashMap<String, Object> map : maps) {
            String userip = map.get("userip").toString();
            try {
                String[] addrs = IP.find(userip);
                System.out.println(userip+" - "+JacksonUtil.toJson(addrs));
                mapper.setLocation(userip,addrs[0],addrs[1],addrs[2]);
            } catch (Throwable e) {
                System.out.println(userip+" - "+"转换失败");
            }
        }

//        this.country = addrs[0];
//        this.region = addrs[1];
//        this.city = addrs[2];
//        System.out.println(JacksonUtil.toJson(maps));
    }

    @Autowired
    ComboPooledDataSource dataSource;

    @Test
    public void testJdbc() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        IP.load("src/main/resources/17monipdb.dat");

        List<HashMap<String, Object>> maps = mapper.findGroupIp();
        int index = 0;
        for (HashMap<String, Object> map : maps) {
            String userip = map.get("userip").toString();
            try {
                String[] addrs = IP.find(userip);
                System.out.println(userip + " - " + JacksonUtil.toJson(addrs) + " - " + index);
                statement.addBatch("UPDATE vote_num SET `parser`=1,city='"+addrs[0]+"',province='"+addrs[1]+"',country='"+addrs[2]+"' WHERE userip='"+userip+"'");
            } catch (Throwable e) {
                System.out.println(userip+" - "+"转换失败");
            }
            if (index++ > 1000) {
                index = 0;
                int[] ints = statement.executeBatch();
                connection.commit();
//                statement = connection.createStatement();
                System.out.println(ints+"------------------------------------");
            }
        }
        int[] ints = statement.executeBatch();
        connection.commit();
        System.out.println(ints+"====================================");
    }


    @Test
    public void testJdbcIp() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        IP.load("src/main/resources/17monipdb.dat");

        List<HashMap<String, Object>> maps = mapper.findIp();
        int index = 0;
        for (HashMap<String, Object> map : maps) {
            String userip = map.get("userip").toString();
            try {
                String[] addrs = IP.find(userip);
                System.out.println(userip + " - " + JacksonUtil.toJson(addrs) + " - " + index);
                statement.addBatch("UPDATE vote_num SET `parser`=1,city='"+addrs[0]+"',province='"+addrs[1]+"',country='"+addrs[2]+"' WHERE userip='"+userip+"'");
            } catch (Throwable e) {
                System.out.println(userip+" - "+"转换失败");
            }
            if (index++ > 1000) {
                index = 0;
                int[] ints = statement.executeBatch();
                connection.commit();
//                statement = connection.createStatement();
                System.out.println(ints+"------------------------------------");
            }
        }
        int[] ints = statement.executeBatch();
        connection.commit();
        System.out.println(ints+"====================================");
    }


    @Test
    public void testJdbcIpDESC() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

        IP.load("src/main/resources/17monipdb.dat");

        List<HashMap<String, Object>> maps = mapper.findIpDESC();
        int index = 0;
        for (HashMap<String, Object> map : maps) {
            String userip = map.get("userip").toString();
            try {
                String[] addrs = IP.find(userip);
                System.out.println(userip + " - " + JacksonUtil.toJson(addrs) + " - " + index);
                statement.addBatch("UPDATE vote_num SET `parser`=1,city='"+addrs[0]+"',province='"+addrs[1]+"',country='"+addrs[2]+"' WHERE userip='"+userip+"'");
            } catch (Throwable e) {
                System.out.println(userip+" - "+"转换失败");
            }
            if (index++ > 1000) {
                index = 0;
                int[] ints = statement.executeBatch();
                connection.commit();
//                statement = connection.createStatement();
                System.out.println(ints+"------------------------------------");
            }
        }
        int[] ints = statement.executeBatch();
        connection.commit();
        System.out.println(ints+"====================================");
    }
}
