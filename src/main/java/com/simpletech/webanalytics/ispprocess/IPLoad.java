package com.simpletech.webanalytics.ispprocess;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * ip文件加载
 * Created by ChenQi on 2015/12/25 17:48.
 */
public class IPLoad {
    private String in_path = "src/main/resources/t_ip.txt";
    private String out_path = "src/main/resources/final_ip.txt";

    /**
     * 读取原始文件
     *
     * @return listIP 将数据保存在list数组中
     * @throws FileNotFoundException
     */
    public List<IPModel> fileLoad() throws FileNotFoundException {
        List<IPModel> listIP=new ArrayList<>();
        System.out.println("读取文件……");
        int num=1;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(in_path), "UTF-8"));
            String str;

            //读取数据，过滤掉长度不为5的数据
            while ((str = br.readLine()) != null) {
                String[] ip = str.split(" ");

                IPModel ipModel = new IPModel();
                if (ip.length == 5) {
//                    if(ip[0]==null){
//                        System.out.println(num);
//                    }
                    ipModel.setsIP(ip[0]);
                    ipModel.seteIP(ip[1]);
                    ipModel.setIsp(ip[2]);
                    ipModel.setsIP_num(Long.parseLong(ip[3]));
                    ipModel.seteIP_num(Long.parseLong(ip[4]));
                }

                listIP.add(ipModel);
                num++;
            }
            br.close();
            //排序
            Collections.sort(listIP, new Comparator<IPModel>() {
                @Override
                public int compare(IPModel o1, IPModel o2) {
                    Long sNum1 = o1.getsIP_num();
                    Long sNum2 = o2.getsIP_num();
                    Long eNum1 = o1.geteIP_num();
                    Long eNum2 = o2.geteIP_num();


//                    return sNum1.compareTo(sNum2);

                    if((sNum1-sNum2)>0){
                        return 1;
                    }else if((sNum1-sNum2)<0){
                        return -1;
                    }else{
                        return eNum1.compareTo(eNum2);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("listIP.size()=" + listIP.size());

        return listIP;
    }

    //逐条读取list中的数据并写入新的文件中
    public void write(List<IPModel> list) {

        try {
            File f = new File(out_path);
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f),"UTF-8");
            BufferedWriter bw=new BufferedWriter(write);
            for (IPModel model : list) {
                String line = model.getsIP() + " " + model.geteIP() + " " + model.getIsp() + " " + model.getsIP_num() + " " + model.geteIP_num();
                bw.write(line);
                bw.newLine();
                bw.flush();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
