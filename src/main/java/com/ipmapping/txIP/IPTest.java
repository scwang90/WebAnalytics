package com.ipmapping.txIP;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/10/27 17:41.
 */
public class IPTest {

    public static void main(String[] args){
        String ip="210.40.16.52";
        String[] ss=txIpParser(ip);
        System.out.println("国家："+ss[0]+"  "+"省份："+ss[1]+"  "+"城市："+ss[2]+"  "+"区/县："+ss[3]+"  "+"运营商："+ss[4]);
    }

    public static String[] txIpParser(String ip){
        String tx_country="",tx_province="",tx_city="",tx_district="",isp="",tx_isp="";
        //指定纯真数据库的文件名，所在文件夹
        TXIP qqip=new TXIP("qqwry.dat","E:\\project\\IPResource");
        //测试IP 58.20.43.13
        String address=qqip.getIPLocation(ip).getCountry();
        isp=qqip.getIPLocation(ip).getArea();
//        String rexEx="CHINANET|CSTNET|CERNET|CHINAGBN|UNINET|CNCNET|CMNET|CIETNET|CGWNET|CSNET";//匹配运营商
          String rexEx="电信|科学技术|教育|金桥|联通|网通|移动|经济贸易|长城|卫星";//匹配运营商
//
        Pattern pattern_isp=Pattern.compile(rexEx,Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern_isp.matcher(isp);
        if(matcher.find()){
            tx_isp=matcher.group(0);

        }else{
            tx_isp=isp;
        }

//        System.out.println(qqip.getIPLocation(ip).getCountry()+":"+qqip.getIPLocation(ip).getArea());
        String[] area={"香港","澳门","宁夏","西藏","广西","新疆","内蒙古"};
        for(String aa:area){
            try {
                if(address.contains(aa)||address.contains("省")||address.contains("市")||address.contains("中国")){
                    tx_country="中国";
                    if(address.contains("省")){//23个省
                        tx_province=address.substring(0,address.indexOf("省")+1);
                    }if(address.contains(aa)&&!address.contains("省")){//自治区和特别行政区
                        tx_province=aa;
                    }
                    if(address.contains("市")&&address.contains("省")){//普通省市
                        tx_city=address.substring(address.indexOf("省")+1,address.indexOf("市")+1);
                    }
                    if(address.contains("市")&&!address.contains("省")&&!address.contains(aa)){//直辖市
                        tx_province=address.substring(0,address.indexOf("市")+1);
                        tx_city=address.substring(0,address.indexOf("市")+1);
                    }
                    if(address.contains("市")&&address.contains(aa)){//自治区下的市
                        tx_province=aa;
                        tx_city=address.substring(address.indexOf(aa),address.indexOf("市")+1);
                    }
                    if(address.contains("区")){
                        tx_district=address.substring(address.indexOf("市")+1,address.indexOf("区")+1);
                    }
                    if(address.contains("县")){
                        tx_district=address.substring(address.indexOf("市")+1,address.indexOf("县")+1);

                    }
                    break;
                }else{
                    tx_country=address;
                    tx_province="";
                    tx_city="";
                    tx_district="";
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("纯真ip转换异常！ip="+ip);
                tx_country=address;
                tx_province="";
                tx_city="";
                tx_district="";
            }

        }
        return new String[]{tx_country,tx_province,tx_city,tx_district,tx_isp};
    }

}
