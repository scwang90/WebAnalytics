package com.simpletech.webanalytics.ipcomparetask;

import com.ipmapping.BDIP;
import com.ipmapping.TBIP1;
import com.ipmapping.txIP.IPTest;
import com.simpletech.webanalytics.model.IpLocation;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.service.IpLocationService;
import com.simpletech.webanalytics.service.IspService;
import com.simpletech.webanalytics.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/10/29 13:20.
 */
@Component
public class DoCompare {
    @Autowired
    IpLocationService ipLocationService;
    @Autowired
    VisitService visitService;
    @Autowired
    IspService ispService;

    @Scheduled(cron = "0 0/1 * * * ?")//每分钟运行一次
    public void doTranslate() {

        try {
            //通过百度API获取运营商信息并插入t_visit中，每次处理前500条数据，因为访问百度接口耗时较长，因此一次处理的数据量不宜过大
            ispService.ispBatch("where location_isp is null group by location_ip order by visit_servertime DESC",500,0);
        }catch (Exception e1){
            e1.printStackTrace();
            System.out.println("地址转换操作失败");
        }
            System.out.println("对比开始");
            //取出所有需要对比的ip等信息
//            List<IpLocation> list = ipLocationService.findAllIp();
//            if(!list.isEmpty()){
//                //取出所有的信息，后期更新时使用
//                List<Visit> vist=visitService.findAll();
//                int i = 1;
//                for (IpLocation str : list) {
//                    String ip = str.getIp();
//                    /**
//                     * 纯真IP转换
//                     */
//                    IPTest txIp = new IPTest();
//                    String[] tx_location = txIp.txIpParser(ip);
//                    System.out.println("TXTranslate()");
//                    str.setTxCountry(tx_location[0]);
//                    str.setTxProvince(tx_location[1]);
//                    str.setTxCity(tx_location[2]);
//                    str.setTxDistrick(tx_location[3]);
//                    str.setTxIsp(tx_location[4]);
//
//                    /**
//                     * 百度转换
//                     */
//                    BDIP bd = new BDIP();
//                    String[] locate = bd.getIPXY(ip);
//                    System.out.println("BDTranslate()");
//                    String[] location = bd.getIPLocation(locate[1], locate[0]);
//                    str.setBdCountry(location[0].toString());
//                    str.setBdProvince(location[1].toString());
//                    str.setBdCity(location[2].toString());
//                    str.setBdDistrick(location[3].toString());
//                    str.setBdIsp(location[4].toString());
//
//                    /**
//                     * 淘宝转换
//                     */
//                    TBIP1 tb=new TBIP1();
//                    System.out.println("TBTranslate()");
//                    String[] tb_location=tb.getTBLocation(ip);
//                    str.setTbCountry(tb_location[0]);
//                    str.setTbProvince(tb_location[1]);
//                    str.setTbCity(tb_location[2]);
//                    str.setTbDistrick(tb_location[3]);
//                    str.setTbIsp(tb_location[4]);
//
//                    ipLocationService.insert(str);
//                    System.out.println(i++);
//                    //更新t_visit中location_compared字段
//                    for(Visit ss:vist){
//                        if(ss.getLocationIp().equals(ip)){
//                            ss.setLocationCompared(true);
//                            visitService.update(ss);
//                        }
//                    }
//                }
//            }else{
//                System.out.println("没有需要对比的IP");
//            }
    }
}
