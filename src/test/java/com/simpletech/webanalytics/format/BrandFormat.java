package com.simpletech.webanalytics.format;

import com.useragent.target.Brand;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 品牌测试
 * Created by SCWANG on 2015-09-27.
 */
public class BrandFormat {

    @Test
    public void parser(){
        String useragent = "GiONEE-GN700T_TD/v1 Linux/3.0.13 Android/4.0.4 Release/01.11.2013 Browser/AppleWebKit534.30/Mobile Safari/534.30 Mobile Android 4.0.4";
        Matcher matcher = Pattern.compile("\\b(mi ?.*|hm ?.*) build\\b", Pattern.CASE_INSENSITIVE).matcher(useragent);
        if (matcher.find()){
            System.out.println("find");
            System.out.println(matcher.group(1));
        } else {
            System.out.println("not find");
        }
        System.out.println(Brand.parser(useragent).getRemark());
        System.out.println(Brand.parser(useragent).getModel());
    }

    @Test
    public void format(){
        String models = "htc|lg|samsung|sonyericsson|sony|asus|onda|woxter|huawei|dell|archos|motorola";
        String[] split = models.split("\\|");
        for (String model : split){
            System.out.printf("%s(\"%s\",\"%s\",\"%s\"),\n",model.toUpperCase(),model,model,model.toUpperCase());
        }
    }

    @Test
    public void code(){
        String text = "HT(\"htc\",\"htc\",\"HTC\"),\n" +
                "    LG(\"\\\\blg\\\\b\",\"lg\",\"LG\"),\n" +
                "    SS(\"samsung\",\"samsung\",\"三星\"),\n" +
                "    SI(\"sonyericsson\",\"sonyericsson\",\"索爱\"),\n" +
                "    SY(\"sony\",\"sony\",\"索尼\"),\n" +
                "    AS(\"asus\",\"asus\",\"华硕\"),\n" +
                "    OA(\"onda\",\"onda\",\"昂达\"),\n" +
                "    WT(\"woxter\",\"woxter\",\"WOXTER\"),\n" +
                "    HW(\"huawei\",\"huawei\",\"华为\"),\n" +
                "    DL(\"dell\",\"dell\",\"戴尔\"),\n" +
                "    AH(\"archos\",\"archos\",\"爱可视\"),\n" +
                "    MT(\"motorola\",\"motorola\",\"摩托罗拉\"),\n" +
                "    XM(\"\\\\bmi\\\\b|xiaomi\",\"xiaomi\",\"小米\"),\n" +
                "    AP(\"apple\\\\b|\\\\bios\\\\b|iphone|ipad|\\\\bmac\\\\b\",\"apple\",\"苹果\"),\n" +
                "    NK(\"nokia|symbian\",\"nokia\",\"诺基亚\"),\n" +
                "    PC(\"windows|linux|wow\",\"computer\",\"电脑\"),\n" +
                "    UN(\"\",\"\",\"未知\"),";
        String[] lines = text.split("\n");
        for (String line : lines) {
//            System.out.println(line);
            Pattern pattern = Pattern.compile("(\\w+)\\((\".*\"),(\".*\"),(\".*\")\\),");
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            System.out.println(matcher.group(1)+"("+matcher.group(3)+","+matcher.group(4)+","+matcher.group(2)+"),");
        }
    }
}
