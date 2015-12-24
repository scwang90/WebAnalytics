package com.ipmapping;

import com.ipmapping.txIP.IPTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/10/16 17:13.
 */
public class BDIP {
    private static String ak = "w5c7qZi8TIb15t6Ftk75aliO";//百度申请的Key
    private static String bd_isp="";

//      private static String ip=IPCatcherUtil.getWebIp();
      private static String ip = "211.71.63.251";
//      private static String ip = new IPTranslator().ip;

    /**
     * 将ip通过百度API转换得到经纬度
     *
     * @param ip 访问者ip
     * @return
     */
    public static String[] getIPXY(String ip) {

        if (null == ip) {
            ip = "";
        }
        try {

            URL url = new URL("http://api.map.baidu.com/location/ip?ak=" + ak
                    + "&ip=" + ip + "&coor=bd09ll");
            InputStream inputStream = url.openStream();
            InputStreamReader inputReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputReader);
            StringBuffer sb = new StringBuffer();
            String str;
            do {
                str = reader.readLine();
                sb.append(str);
            } while (null != str);

            str = sb.toString();
//            if (null == str || str.isEmpty()) {//即使错误也会有错误信息
//                return null;
//            }
            //获取运营商
            String rexEx="CHINANET|CSTNET|CERNET|CHINAGBN|UNINET|CNCNET|CMNET|CIETNET|CGWNET|CSNET|UNICOM|CRTC|NET";//匹配运营商
            String rexEx1="(\\w+?)\\|\\d+";//匹配运营商

            Pattern pattern_isp=Pattern.compile(rexEx1,Pattern.CASE_INSENSITIVE);
            Matcher matcher=pattern_isp.matcher(str);
            if(matcher.find()){
                String ispStr=matcher.group(1);
//                switch (ispStr){
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
//                        //调用纯真IP再次解析
//                        IPTest cz=new IPTest();
//                        String[] cz_location=cz.txIpParser(ip);
//                        bd_isp=cz_location[4].replace("\"","");
//                        break;
//                    }
//                    default:bd_isp=ispStr;
//                }
                bd_isp=ispStr;

            }else{
                //若匹配失败则保存IP信息
                bd_isp="unknown";
            }
            // 获取坐标位置
            if(!str.contains("failed")){
                int index = str.indexOf("point");
                int end = str.indexOf("}}", index);
                if (index == -1 || end == -1) {
                    return null;
                }
                str = str.substring(index - 1, end + 1);
                if (null == str || str.isEmpty()) {
                    return null;
                }
                String[] ss = str.split(":");
                if (ss.length != 4) {
                    return null;
                }
                String x = ss[2].split(",")[0];
                String y = ss[3];
                x = x.substring(x.indexOf("\"") + 1, x.indexOf("\"", 1));
                y = y.substring(y.indexOf("\"") + 1, y.indexOf("\"", 1));

                return new String[]{x, y,bd_isp};
            }
        } catch (UnknownHostException e){
            System.out.println("百度接口访问失败");
        }
        catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{null, null,bd_isp};
    }

    /**
     * 根据经纬度获取位置信息
     * @param x 经度
     * @param y 纬度
     * @return
     */
    public static String[] getIPLocation(String x, String y) {
        if(x!=null&&y!=null){
            try {

                URL url = new URL("http://api.map.baidu.com/geocoder/v2/?ak="+ak+"&callback=renderReverse&location="+x+","+y+"&output=json");
//                InputStream inputStream = url.openStream();
//                InputStreamReader inputReader = new InputStreamReader(inputStream);
                //BufferedReader reader = new BufferedReader(inputReader);
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
                // 获取所在位置
                String bd_country,bd_city,bd_province,bd_district;
                int index = str.indexOf("addressComponent");
                int end = str.indexOf("},", index);
                if (index == -1 || end == -1) {
                    return null;
                }
                str = str.substring(index - 1, end + 1);
                if (null == str || str.isEmpty()) {
                    return null;
                }
                String[] ss = str.split(":");
                //String[] ss1=ss.;
                // System.out.println(ss.length);
//            for(int j=0;j<ss.length;j++){
//                System.out.println(ss[j]);
//            }
                if (ss.length != 11) {
                    return null;
                }
                bd_country=ss[3].split(",")[0];
                bd_city=ss[2].split(",")[0];
                bd_province=ss[7].split(",")[0];
                bd_district=ss[6].split(",")[0];
//            bd_isp=getIPXY(ip)[2];
                return new String[]{bd_country,bd_province,bd_city,bd_district,bd_isp};
            }catch (UnknownHostException e){
                System.out.println("百度API连接异常");
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String[]{"", "","","",bd_isp};
    }

//    public static String getBd_isp(String str){
//        //获取运营商
//        String rexEx="CHINANET|CSTNET|CERNET|CHINAGBN|UNINET|CNCNET|CMNET|CIETNET|CGWNET|CSNET";//匹配运营商
//        String rexEx1="(\\w+?)\\|\\d+";//匹配运营商
//        Pattern pattern_isp=Pattern.compile(rexEx1,Pattern.CASE_INSENSITIVE);
//        Matcher matcher=pattern_isp.matcher(str);
//        if(matcher.find()){
//            bd_isp=matcher.group(1);
//
//        }else{
//            bd_isp="";
//        }
//        return bd_isp;
//    }
    public static void main(String []args){
        System.out.println("ip=" + ip );
        String[] locate=getIPXY(ip);
        if(locate!=null){
            System.out.println(" 经纬度:x="+locate[0]+"\t"+"y="+locate[1]+"\t"+"isp="+locate[2]);
            String[] location=getIPLocation(locate[1],locate[0]);
            for(int i=0;i<location.length;i++){
                System.out.print(location[i]);
            }
        }


    }
}
