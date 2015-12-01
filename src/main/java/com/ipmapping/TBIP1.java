package com.ipmapping;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/10/29 9:53.
 */
public class TBIP1 {


    public static String[] getTBLocation(String ip){
        String path = "http://ip.taobao.com/service/getIpInfo.php?ip="+ip;
        String info;
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("GET");// 提交方法POST|GET  但是淘宝以GET的方式接受请求
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes("ip="+ip);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), "utf-8"));// 往对端写完数据对端服务器返回数据,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            do {
                info = reader.readLine();
                buffer.append(info);
            } while (null != info);
            info = buffer.toString();
//            System.out.println(info);
            reader.close();
            // 获取位置信息
            int index = info.indexOf("data");
            int end = info.indexOf("}}", index);
            if (index == -1 || end == -1) {
                return null;
            }
            info = info.substring(index - 1, end + 1);

            if (null == info || info.isEmpty()) {
                return null;
            }
            String[] ss = info.split(":");
//            System.out.println(ss.length);
            if (ss.length != 15) {
                return null;
            }
//            for(int i=0;i<ss.length;i++){
//                System.out.println(ss[i]);
//            }
            String tb_country =decodeUnicode( ss[2].split(",")[0]);
            String tb_province = decodeUnicode(ss[6].split(",")[0]);
            String tb_city = decodeUnicode(ss[8].split(",")[0]);
            String tb_district ="";//因为淘宝的精度只到市级
            String tb_isp= decodeUnicode(ss[12].split(",")[0]);
            return new String[]{tb_country, tb_province,tb_city,tb_district,tb_isp};
        } catch (IOException e) {
            System.out.println("连接服务器失败");
            return new String[]{"服务器未响应", "服务器未响应","服务器未响应","服务器未响应","服务器未响应"};
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
    }
    //unicode转中文
    public static String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len;) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }
    public static void main(String []args){
        String ip = "210.40.16.52";
        System.out.println("ip=" + ip );
        String[] ss1=getTBLocation(ip);
        for(int i=0;i<ss1.length;i++){
            System.out.print(ss1[i].toString());
        }

    }
}
