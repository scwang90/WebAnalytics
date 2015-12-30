package com.simpletech.webanalytics.ispprocess;

import java.io.FileNotFoundException;

/**
 * test
 * Created by ChenQi on 2015/12/28 11:23.
 */
public class TestLoad {
    public static void main(String []args){
        String addr = "210.40.2.236";
        IPLoad load=new IPLoad();
        ISPParse isp=new ISPParse();
//        try {
//            List<IPModel> listIP=load.fileLoad();
//
//            System.out.println("写入中……");
//            //写入文件
//            load.write(listIP);
//            System.out.println("处理完成");
//        }catch (FileNotFoundException e){
//            System.err.println("文件不存在");
//        }
        try {
            isp.ispParser(addr);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
