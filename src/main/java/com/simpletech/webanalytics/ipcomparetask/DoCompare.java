package com.simpletech.webanalytics.ipcomparetask;

import com.ipmapping.BDIP;
import com.ipmapping.TBIP1;
import com.ipmapping.txIP.IPTest;
import com.simpletech.webanalytics.dao.base.MultiDao;
import com.simpletech.webanalytics.model.Bdip;
import com.simpletech.webanalytics.model.IpLocation;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.service.IspService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/10/29 13:20.
 */
@Component
public class DoCompare {

    @Autowired
    MultiDao<Visit> visitMultiDao;
    @Autowired
    MultiDao<IpLocation> ipLocationMultiDao;
    @Autowired
    MultiDao<Bdip> bdipMultiDao;
    @Autowired
    IspService ispService;

    @Scheduled(cron = "0 0/1 * * * ?")//每分钟运行一次
    public void doTranslate() {
        try {
            //通过百度API获取运营商信息并插入t_visit中，每次处理前250条数据，因为访问百度接口耗时较长，因此一次处理的数据量不宜过大
//            ispService.ispBatch("where location_isp is null group by location_ip order by visit_servertime DESC",250,0);
            ispService.ispLocalBatch("where location_isp is null group by location_ip order by visit_servertime DESC",250,0);
        }catch (Exception e1){
            e1.printStackTrace();
            System.out.println("地址转换操作失败");
        }
            System.out.println("对比开始");

        try {
            //取出需要对比的前250条ip信息
//            ipLocationMultiDao.findWhereByPage("where location_compared is null group by location_ip order by visit_servertime", 250, 0);
//            List<IpLocation> list = ipLocationDao.findVisitWhereByPage("where location_compared is null group by location_ip order by visit_servertime", 250, 0);
            List<Visit> list1=visitMultiDao.findWhereByPage("where location_compared is null group by location_ip order by visit_servertime", 250, 0);
            //取出所有的信息，后期更新时使用
            List<Visit> vist=visitMultiDao.findWhereByPage("where location_compared is null group by location_ip order by visit_servertime", 250, 0);
            List<IpLocation> batchList=new ArrayList<>();
            if(!list1.isEmpty()){

                int i = 1;
                for (Visit str : list1) {
                    IpLocation ipLocation=new IpLocation();

                    String ip = str.getLocationIp();
                    String id=str.getId();
                    ipLocation.setId(id);
                    ipLocation.setIp(ip);
                    ipLocation.setIpipCity(str.getLocationCity());
                    ipLocation.setIpipCountry(str.getLocationCountry());
                    ipLocation.setIpipProvince(str.getLocationRegion());
                    /**
                     * 纯真IP转换
                     */
                    IPTest txIp = new IPTest();
                    String[] tx_location = txIp.txIpParser(ip);
                    System.out.println("TXTranslate()");
                    ipLocation.setTxCountry(tx_location[0]);
                    ipLocation.setTxProvince(tx_location[1]);
                    ipLocation.setTxCity(tx_location[2]);
                    ipLocation.setTxDistrick(tx_location[3]);
                    ipLocation.setTxIsp(tx_location[4]);
                    System.out.println("txIsp="+tx_location[4]);
                    /**
                     * 百度转换
                     */
                    Bdip bdip=new Bdip();
                    BDIP bd = new BDIP();
                    String[] locate = bd.getIPXY(ip);
                    System.out.println("BDTranslate()");
                    String[] location = bd.getIPLocation(locate[1], locate[0]);
                    ipLocation.setBdCountry(location[0].toString());
                    ipLocation.setBdProvince(location[1].toString());
                    ipLocation.setBdCity(location[2].toString());
                    ipLocation.setBdDistrick(location[3].toString());
                    ipLocation.setBdIsp(location[4].toString());
                    //向t_bdip中插入数据
                    bdip.setId(id);
                    bdip.setBdIp(ip);
                    bdip.setBdCountry(location[0].toString());
                    bdip.setBdRegion(location[1].toString());
                    bdip.setBdCity(location[2].toString());
                    bdip.setBdIsp(location[4].toString());
                    System.out.println("百度插入^^^^^^");
                    try {
                        bdipMultiDao.insert(bdip);
                    }catch (DuplicateKeyException e){
                        System.out.println("该值已存在");
                    }

                    /**
                     * 淘宝转换
                     */
                    TBIP1 tb=new TBIP1();
                    System.out.println("TBTranslate()");
                    String[] tb_location=tb.getTBLocation(ip);
                    ipLocation.setTbCountry(tb_location[0]);
                    ipLocation.setTbProvince(tb_location[1]);
                    ipLocation.setTbCity(tb_location[2]);
                    ipLocation.setTbDistrick(tb_location[3]);
                    ipLocation.setTbIsp(tb_location[4]);

                    batchList.add(ipLocation);
                    try{
                        ipLocationMultiDao.insert(ipLocation);
                        System.out.println(i++);
                        //更新t_visit中location_compared字段
                        for(Visit ss:vist){
                            if(ss.getLocationIp().equals(ip)){
                                ss.setLocationCompared(true);
                                visitMultiDao.update(ss);
                                System.out.println("update()");
                            }
                        }
                    }catch (Exception e){
                        System.out.println("ip对比插入失败"+e);

                    }

//                    ipLocationDao.insert(str);

                }

            }else{
                System.out.println("没有需要对比的IP");
            }
        }catch (Exception e){
            System.out.println("转换失败");
            e.printStackTrace();
        }
    }
}
