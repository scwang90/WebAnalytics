package com.simpletech.webanalytics.service.impl;

import com.ipmapping.BDIP;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.simpletech.webanalytics.dao.IspDao;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.entity.IspValue;
import com.simpletech.webanalytics.service.IspService;
import com.simpletech.webanalytics.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * 数据库表t_visit的Service接实现
 *
 * @author 树朾
 * @date 2015-10-15 12:18:28 中国标准时间
 */
@Service
public class IspServiceImpl implements IspService {

    @Autowired
    IspDao dao;

    @Autowired
    ComboPooledDataSource dataSource;

    //格式化日期
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

    @Override
    public List<Visit> findWhereIsp(String where, int limit, int start) throws Exception {
        List<Visit> visits = dao.findWhereIsp(where, limit, start);
        List<Visit> newVisit = new ArrayList<>();
//		IPTest txIp = new IPTest();
        for (Visit vv : visits) {
            //提取运营商信息
            BDIP bd = new BDIP();
            String isp = bd.getIPXY(vv.getLocationIp())[2];
//			String[] tx_location = txIp.txIpParser(vv.getLocationIp());
//			vv.setLocationIsp(tx_location[4]);
//			vv.setLocationIsp(null);
            vv.setLocationIsp(isp);
            vv.setUpdateTime(new Date());
            dao.updateIsp(vv);
            newVisit.add(vv);
        }
        return newVisit;
    }

    @Override
    public List<HashMap<String, Object>> ispBatch(String where, int limit, int start) throws Exception {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();

//        System.out.println("connected");
        long c=System.currentTimeMillis();
        List<HashMap<String, Object>> maps = dao.ispBatch(where, limit, start);
        System.out.println("查找" + limit + "条数据耗时：" + (System.currentTimeMillis() - c) / 1000f + "秒");
        try {

            long b=System.currentTimeMillis();
            for (HashMap<String, Object> map : maps) {
                String ip = map.get("location_ip").toString();
//                String useragent = map.get("useragent").toString();
//				String id=map.get("id").toString();
//                System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//				System.out.println("id="+ id);
//                System.out.println("ip=" + ip);
//                System.out.println("useragent=" + useragent);
                try {
                    BDIP bdip = new BDIP();
                    String isp = bdip.getIPXY(ip)[2];
//                    System.out.println("ip->isp=" + ip + isp);
                    
                    statement.addBatch("UPDATE t_visit SET update_time='"+df.format(new Date()).toString()+"',  location_isp= '" + isp + "' WHERE location_ip = '" + ip + "'");

                } catch (Throwable e) {
                    System.out.println(ip + " - " + "转换失败");
                    e.printStackTrace();
                }

            }
            System.out.println("百度转换"+limit+"条数据耗时：" + (System.currentTimeMillis() - b) / 1000f + "秒");
            long a=System.currentTimeMillis();
            int[] ints = statement.executeBatch();
            connection.commit();
            System.out.println("更新" + limit + "条数据耗时：" + (System.currentTimeMillis() - a) / 1000f + "秒");
//            System.out.println(ints + "------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return maps;
    }

    @Override
    public List<IspValue> isp(String siteId, Date start, Date end) throws Exception {
        List<IspValue> values = dao.isp(siteId, start, end);
        List<IspValue> newvalues = new ArrayList<>();
        int count = 0;
        for (IspValue ispValue : values) {
            count += ispValue.getNum();
        }

        for (IspValue isp : values) {
            int num = isp.getNum();
            isp.setRate(1f * isp.getNum() / count);
            newvalues.add(isp);
        }
        return newvalues;
    }
}
