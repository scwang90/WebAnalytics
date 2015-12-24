package com.simpletech.webanalytics.dao.impl;

import com.ipmapping.BDIP;
import com.ipmapping.cnIP.IPCalculate;
import com.simpletech.webanalytics.dao.IPDao;
import com.simpletech.webanalytics.mapper.IPMapper;
import com.simpletech.webanalytics.model.IP;
import com.simpletech.webanalytics.model.IpLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/12/11 16:04.
 */
@Repository
public class IPDaoImpl implements IPDao {
    @Autowired
    IPMapper ipMapper;

//    public List<String> find(String ip) throws Exception{
//
//        IPCalculate ipCalculate=new IPCalculate();
//        long num=ipCalculate.validateIP(ip);
//
//        List<String> list=new ArrayList<>();//存储
//
//        String str=ip+"\t"+num;
//        list.add(str);
//
//        /**
//         * 百度转换
//         */
//        list.add("baidu:");
//        BDIP bd = new BDIP();
//        String[] locate = bd.getIPXY(ip);
//        String[] location = bd.getIPLocation(locate[1], locate[0]);
//        IpLocation ipLocation=new IpLocation();
//        ipLocation.setBdCountry(location[0].toString());
//        ipLocation.setBdProvince(location[1].toString());
//        ipLocation.setBdCity(location[2].toString());
//        ipLocation.setBdDistrick(location[3].toString());
//        ipLocation.setBdIsp(location[4].toString());
//        String bd_isp=ipLocation.getBdIsp();
//        switch (bd_isp){
//                    case "CHINANET":
//                        bd_isp="电信";
//                        break;
//                    case "UNICOM":
//                        bd_isp="联通";
//                        break;
//                    case "CRTC":
//                        bd_isp="铁通";
//                        break;
//                    case "CSTNET":
//                        bd_isp="科技网";
//                        break;
//                    case "CERNET":
//                        bd_isp="教育网";
//                        break;
//                    case "NET":
//                        bd_isp="天威视讯";
//                        break;
//                    case "CHINAGBN":
//                        bd_isp="金桥网";
//                        break;
//                    case "UNINET":
//                        bd_isp="联通";
//                        break;
//                    case "CNCNET":
//                        bd_isp="网通";
//                        break;
//                    case "CMNET":
//                        bd_isp="移动";
//                        break;
//                    case "CIETNET":
//                        bd_isp="经贸网";
//                        break;
//                    case "CGWNET":
//                        bd_isp="长城";
//                        break;
//                    case "CSNET":
//                        bd_isp="卫星网";
//                        break;
//                    case "OTHER":{
//
//                        bd_isp="OTHER";
//                        break;
//                    }
//                }
//        list.add(ipLocation.getBdProvince()+"  "+ipLocation.getBdCity()+"  "+bd_isp);
//
//        list.add("t_ip:");
//        List<IP> ipList1=ipMapper.find(num);
//        for(IP ip1:ipList1){
//            list.add(ip1.getProvince()+"  "+ip1.getCity()+"  "+ip1.getIsp());
//        }
//        list.add("\n");
//        return list;
//    }
//
//    @Override
//    public int fileWrite(File file,String ip){
//
//        try {
//            FileWriter fw=new FileWriter(file,true);
//            BufferedWriter bw=new BufferedWriter(fw);
//            List<String> ss=find(ip);
//            for(int t=0;t<ss.size();t++){//外层循环，取出各List
//                String qq=ss.get(t);
//                bw.write(qq);
//                bw.newLine();
//                bw.flush();
//            }
//            bw.close();
//            return 1;
//        }catch (IOException e){
//            e.printStackTrace();
//            return 0;
//        }catch (Exception e){
//            e.printStackTrace();
//            return 0;
//        }
//    }

    @Override
    public List<IP> find(long num){
        return ipMapper.find(num);
    }
}
