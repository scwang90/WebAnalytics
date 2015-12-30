package com.ipmapping.cnIP;


/**
 * 将IP地址转换为整型
 * Created by ChenQi on 2015/12/10 17:12.
 */
public class IPCalculate {

    public static int validateIP(String ip) {

        String[] ipArray = ip.split("\\.");
        int[] ipNum = new int[ipArray.length];
        for (int i = 0; i < ipArray.length; i++) {
            ipNum[i] = Integer.parseInt(ipArray[i].trim());
        }

        // 各个数字乘以相应的数量级
        int ipNumTotal = ipNum[0] * 256 * 256 * 256 + ipNum[1] * 256 * 256 + ipNum[2] * 256 + ipNum[3];

        return ipNumTotal;
    }
}
