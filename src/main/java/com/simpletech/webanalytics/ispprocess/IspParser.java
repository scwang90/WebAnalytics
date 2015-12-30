package com.simpletech.webanalytics.ispprocess;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 运营商转换工具
 * Created by root on 15-12-30.
 */
public class IspParser {

    // 用来做为cache，查询一个ip时首先查看cache，以减少不必要的重复查找
    private static Map<Long[], String> dataBuffer;

    public synchronized static void load(String resource, String charset) {
        if (dataBuffer == null) {
            Map<Long[], String> buffer = new HashMap<>();//存储ip和相应ip信息
            try (InputStream input = IspParser.class.getClassLoader().getResourceAsStream(resource)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(input, charset));
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] ip = line.split(" ");
                    if (ip.length == 5) {
                        Long[] key = {Long.parseLong(ip[3]), Long.parseLong(ip[4])};
                        buffer.put(key, ip[2]);
                    }
                }
                dataBuffer = buffer;
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }

    public static String ispParser(String ip) {
        load("ip-isp.txt", "UTF-8");
        //取出所有key的集合
        Set<Long[]> set = dataBuffer.keySet();
        //计算出ip地址对应的长整数
        Long ipnum = ipToLong(ip);
        //查找ip对应的记录
        for (Long[] num : dataBuffer.keySet()) {
            if (ipnum >= num[0] && ipnum <= num[1]) {
                return dataBuffer.get(num);
            }
        }
        return "未知";
    }

    public static long ipToLong (String ip) {
        String[] bytes = ip.split("\\.");
        if (bytes.length == 4) {
            int[] ipNum = new int[bytes.length];
            for (int i = 0; i < bytes.length; i++) {
                ipNum[i] = Integer.parseInt(bytes[i]);
            }
            return ((long)ipNum[0] << 24) | (ipNum[1] << 16) | (ipNum[2] << 8) | ipNum[3];
        }
        return -1;
    }

}
