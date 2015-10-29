package com.ipmapping;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 淘宝IP库解析
 * Created by ChenQi on 2015/10/16 17:13.
 */
public class TBIP {
    private static String ip = "10.126.226.144";
    public static String[] getTBLocation(String ip) {
//        String tb_country, tb_province,tb_city,"UNKNOWN",tb_isp
        if (null == ip) {
            ip = "";
        }
        try {

            URL url = new URL("http://ip.taobao.com/service/getIpInfo.php?ip="+ip);
            InputStream inputStream = url.openStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
           // BufferedReader reader = new BufferedReader(inputReader);
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    url.openStream(), "utf-8"));
            StringBuffer sb = new StringBuffer();
            String str;
            do {
                str = reader.readLine();
                sb.append(str);
            } while (null != str);

            str = sb.toString();
            if (null == str || str.isEmpty()) {
                return null;
            }
            // 获取位置信息
            int index = str.indexOf("data");
            int end = str.indexOf("}}", index);
            if (index == -1 || end == -1) {
                return null;
            }
            str = str.substring(index - 1, end + 1);

            if (null == str || str.isEmpty()) {
                return null;
            }
            String[] ss = str.split(":");
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
            String tb_district ="UNKNOWN";//因为淘宝的精度只到市级
            String tb_isp= decodeUnicode(ss[12].split(",")[0]);
            return new String[]{tb_country, tb_province,tb_city,tb_district,tb_isp};
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
        System.out.println("ip=" + ip );
        String[] ss1=getTBLocation(ip);
        for(int i=0;i<ss1.length;i++){
            System.out.print(ss1[i].toString());
        }

    }
}
