package com.ipmapping.cnIP;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/12/10 17:17.
 */
public class Test {
    public static void main(String []args){
//        RandomIP rdIP=new RandomIP();
//        String ip=rdIP.getRandomIp();
        IPCalculate ipCalculate=new IPCalculate();
        long num=ipCalculate.validateIP("10.33.46.60");

//        String str=ip+"\t"+num;
//        File writer=new File("E:\\ipCompare.txt");
//        try {
//            FileWriter fw=new FileWriter(writer,true);
//            BufferedWriter bw=new BufferedWriter(fw);
//            bw.write(str);
//            bw.newLine();
//            bw.flush();
//            bw.close();
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        System.out.println(num);
    }
}
