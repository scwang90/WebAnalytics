package com.simpletech.webanalytics.IspProcess;

import com.ipmapping.cnIP.IPCalculate;

import java.io.*;
import java.util.*;

/**
 * isp解析
 * Created by ChenQi on 2015/12/25 17:48.
 */
public class ISPParse {
    // 用来做为cache，查询一个ip时首先查看cache，以减少不必要的重复查找
    private Map<Long[], IPModel> ipCache;
    private String path = "src/main/resources/t_ip.txt";


    public IPModel ispParser(String addr) throws FileNotFoundException {
        ipCache = new HashMap<Long[], IPModel>();//存储ip和相应ip信息
        IPCalculate ipcalc = new IPCalculate();

        IPModel model = new IPModel();//从HashMap中获取ip对应的实体
        List<IPModel> list=new ArrayList<>();//存放查询结果
        //将文件读入HashMap中
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));

            String str;

            while ((str = br.readLine()) != null) {
                String[] ip = str.split(" ");
                IPModel ipModel = new IPModel();//将记录转换为实体
                if (ip.length == 5) {
                    ipModel.setsIP(ip[0]);
                    ipModel.seteIP(ip[1]);
                    ipModel.setIsp(ip[2]);
                    ipModel.setsIP_num(Long.parseLong(ip[3]));
                    ipModel.seteIP_num(Long.parseLong(ip[4]));
//                    Long[] key = {ipModel.getsIP_num(), ipModel.geteIP_num()};
                    Long[] key = {Long.parseLong(ip[3]), Long.parseLong(ip[4])};
                    ipCache.put(key, ipModel);
                }

            }
            br.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //取出所有key的集合
        Iterator iter = ipCache.entrySet().iterator();
//
//        while (iter.hasNext()) {
//            Map.Entry entry = (Map.Entry) iter.next();
//            Object key = entry.getKey();
//            Object val = entry.getValue();
//
//            System.out.print(key+" "+val);
//        }

        Set<Long[]> set = ipCache.keySet();
        //计算出ip地址对应的长整数
        long ipnum = ipcalc.validateIP(addr);
        String isp = null;
        System.out.println("ip:" + addr + "=>num:" + ipnum);
        //查找ip对应的记录
        for (Long[] num : set) {


            if (ipnum >= num[0] && ipnum <= num[1]) {
                model = ipCache.get(num);
                list.add(model);
//                isp = model.getIsp();
//                System.out.println(model.getsIP_num() + " " + model.geteIP_num() + "---->" + isp);
            }

        }
        if(list.size()>0){
            isp=list.get(0).getIsp();
            System.out.println("ip:"+addr+ "---->" + isp);
        }else{
            isp="未知";
            System.out.println("ip:"+addr+ "---->" + isp);
        }
        return model;

    }

}
