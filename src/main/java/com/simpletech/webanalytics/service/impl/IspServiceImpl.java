package com.simpletech.webanalytics.service.impl;

import com.ipmapping.BDIP;
import com.ipmapping.cnIP.IPCalculate;
import com.ipmapping.txIP.IPTest;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.simpletech.webanalytics.dao.IPDao;
import com.simpletech.webanalytics.dao.IspDao;
import com.simpletech.webanalytics.model.IP;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.entity.IspValue;
import com.simpletech.webanalytics.service.IspService;
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
    IPDao ipDao;
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

        Connection connection = null;
        Statement statement = null;
//        System.out.println("connected");
        long c = System.currentTimeMillis();
        List<HashMap<String, Object>> maps = dao.ispBatch(where, limit, start);
        List<String> batchList = new ArrayList<>();
        System.out.println("查找" + limit + "条数据耗时：" + (System.currentTimeMillis() - c) / 1000f + "秒");
        int num = 0;
        if (maps.size() > 0) {
            try {

                long b = System.currentTimeMillis();
                for (HashMap<String, Object> map : maps) {
                    String ip = map.get("location_ip").toString();
//                String useragent = map.get("useragent").toString();
//				String id=map.get("id").toString();
                    System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
//				System.out.println("id="+ id);
                    System.out.println("ip=" + ip);
//                System.out.println("useragent=" + useragent);
                    try {
                        BDIP bdip = new BDIP();
                        //判断是否取得结果
                        String[] ispStr = bdip.getIPXY(ip);
                        String isp;

                        if (ispStr != null) {
                            isp = ispStr[2];
                            if (isp != null) {
                                System.out.println("ip->isp=" + ip + isp);

                                String sql = "UPDATE t_visit SET update_time='" + df.format(new Date()).toString() + "',  location_isp= '" + isp + "' WHERE location_ip = '" + ip + "'";
                                batchList.add(sql);
//                            statement.addBatch("UPDATE t_visit SET update_time='"+df.format(new Date()).toString()+"',  location_isp= '" + isp + "' WHERE location_ip = '" + ip + "'");

                            } else {
                                isp = "未知";
                                System.out.println("ip->isp=" + ip + isp);
                                String sql = "UPDATE t_visit SET update_time='" + df.format(new Date()).toString() + "',  location_isp= '" + isp + "' WHERE location_ip = '" + ip + "'";
                                batchList.add(sql);
//                            statement.addBatch("UPDATE t_visit SET update_time='"+df.format(new Date()).toString()+"',  location_isp= '" + isp + "' WHERE location_ip = '" + ip + "'");

                            }
                        } else {
                            System.out.println("参数错误，百度接口无法解析该IP，调用纯真IP再次解析");
                            //调用纯真IP再次解析
                            IPTest cz = new IPTest();
                            String[] cz_location = cz.txIpParser(ip);
                            isp = cz_location[4].replace("\"", "");
                            System.out.println("ip->isp=" + ip + isp);
                            String sql = "UPDATE t_visit SET update_time='" + df.format(new Date()).toString() + "',  location_isp= '" + isp + "' WHERE location_ip = '" + ip + "'";
                            batchList.add(sql);
                        }

                    } catch (Throwable e) {
                        System.out.println(ip + " - " + "转换失败");
                        num++;
                        e.printStackTrace();
                    }

                }
                System.out.println("百度转换" + (limit - num) + "条数据耗时：" + (System.currentTimeMillis() - b) / 1000f + "秒");


                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                statement = connection.createStatement();
                for (int i = 0; i < batchList.size(); i++) {
                    statement.addBatch(batchList.get(i));
                }
                long a = System.currentTimeMillis();
                int[] ints = statement.executeBatch();
                connection.commit();
                System.out.println("更新" + limit + "条数据耗时：" + (System.currentTimeMillis() - a) / 1000f + "秒");
                System.out.println(ints + "------------------------------------");
                statement.clearBatch();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                statement.close();
                connection.close();
            }
        } else {
            System.out.println("无待处理记录");
        }

        return maps;
    }

    /**
     * 从ip数据库查找运营商信息
     * @param where
     * @param limit
     * @param start
     * @return
     * @throws Exception
     */
    @Override
    public List<HashMap<String, Object>> ispLocalBatch(String where, int limit, int start) throws Exception {

        Connection connection = null;
        Statement statement = null;
//        System.out.println("connected");
        long c = System.currentTimeMillis();
        List<HashMap<String, Object>> maps = dao.ispBatch(where, limit, start);
        System.out.println("查找" + limit + "条数据耗时：" + (System.currentTimeMillis() - c) / 1000f + "秒");

        List<String> batchList = new ArrayList<>();
        IPCalculate ipCalculate = new IPCalculate();
        int num = 0;
        if (maps.size() > 0) {
            try {
                String isp = null;
                long b = System.currentTimeMillis();
                for (HashMap<String, Object> map : maps) {
                    String ip = map.get("location_ip").toString();
                    System.out.println("ip=" + ip);
                    long ipNum = ipCalculate.validateIP(ip);
                    try {
                        List<IP> ipList = ipDao.find(ipNum);

                        //判断是否取得结果
                        if (ipList.size() > 0) {
                            isp = ipList.get(0).getIsp().toString();
                            //移通网络确实存在
//                            if(isp.equals("移通")){
//                                isp="铁通";
//                            }
                            System.out.println("ip->isp=" + ip + isp);

                            String sql = "UPDATE t_visit SET update_time='" + df.format(new Date()).toString() + "',  location_isp= '" + isp + "' WHERE location_ip = '" + ip + "'";
                            batchList.add(sql);
                        } else {
                            isp="unknown";
                            System.out.println("ip->isp=" + ip + isp);
                            String sql = "UPDATE t_visit SET update_time='" + df.format(new Date()).toString() + "',  location_isp= '" + isp + "' WHERE location_ip = '" + ip + "'";
                            batchList.add(sql);
                        }

                    } catch (Exception e) {
                        isp="unknown";
                        String sql = "UPDATE t_visit SET update_time='" + df.format(new Date()).toString() + "',  location_isp= '" + isp + "' WHERE location_ip = '" + ip + "'";
                        batchList.add(sql);
                        num++;
                        e.printStackTrace();
                    }
                }
                System.out.println("ip库转换" + (limit - num) + "条数据耗时：" + (System.currentTimeMillis() - b) / 1000f + "秒");

                connection = dataSource.getConnection();
                connection.setAutoCommit(false);
                statement = connection.createStatement();
                for (int i = 0; i < batchList.size(); i++) {
                    statement.addBatch(batchList.get(i));
                }
                long a = System.currentTimeMillis();
                statement.executeBatch();
                connection.commit();
                System.out.println("更新" + limit + "条数据耗时：" + (System.currentTimeMillis() - a) / 1000f + "秒");
                statement.clearBatch();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                statement.close();
                connection.close();
            }
        } else {
            System.out.println("无待处理记录");
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
