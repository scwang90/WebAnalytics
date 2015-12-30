package com.simpletech.webanalytics.format;

import com.useragent.target.Platform;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 设备测试
 * Created by SCWANG on 2015-09-27.
 */
public class DeviceFormat {

    @Test
    public void parser(){
        String useragent = "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-cn; MI 3 Build/KOT49H) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025468 Mobile Safari/533.1 QQJSSDK/1.3  Qzone/V1_AND_QZ_5.8.1_288_YYB_D QZONEJSSDK/5.7";
        System.out.println(Pattern.compile("(\\bmi \\S*|\\bhm \\S*)",Pattern.CASE_INSENSITIVE).matcher(useragent).find());
        System.out.println(Platform.parser(useragent).getRemark());
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
