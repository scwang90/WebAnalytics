package com.simpletech.webanalytics.format;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 代码格式化
 * Created by 树朾 on 2015/9/28.
 */
public class CodeFormat {

    @Test
    public void code(){
        String text = "\n" +
                "    Weixin(\"micromessenger\",\"WX\",\"微信浏览器\",\"micromessenger/([\\\\w\\\\.]+)\"),\n" +
                "    Baidu(\"baidu\",\"BD\",\"百度浏览器\",\"baidu\\\\w*/([\\\\w\\\\.]+)\"),\n" +
                "    QQBrowser(\"qq\",\"QQ\",\"QQ浏览器\",\"\\\\bqq\\\\w*/([\\\\w\\\\.]+)\"),\n" +
                "    Xiaomi(\"xiaomi\",\"XM\",\"小米浏览器\",\"xiaomi\\\\w*/([\\\\w\\\\.]+)\"),\n" +
                "    Miui(\"miui\",\"MU\",\"MIUI浏览器\",\"miui\\\\w*/([\\\\w\\\\.]+)\"),\n" +
                "    Edge(\"edge\",\"EDGE\",\"微软 EDGE\",\"\\\\bedge[ /]?([\\\\w\\\\.]*)\"),\n" +
                "    Msie11(\"msie11\",\"IE11\",\"Internet Explorer 11\",\"\\\\bmsie[ :]+(11[\\\\w\\\\.]+)|rv:(1[\\\\w\\\\.]+)\"),\n" +
                "    Msie5(\"msie5\",\"IE5\",\"Internet Explorer 5\",\"\\\\bmsie[ :]+(5[\\\\w\\\\.]+)\"),\n" +
                "    Msie6(\"msie6\",\"IE6\",\"Internet Explorer 6\",\"\\\\bmsie[ :]+(6[\\\\w\\\\.]+)\"),\n" +
                "    Msie7(\"msie7\",\"IE7\",\"Internet Explorer 7\",\"\\\\bmsie[ :]+(7[\\\\w\\\\.]+)\"),\n" +
                "    Msie8(\"msie8\",\"IE8\",\"Internet Explorer 8\",\"\\\\bmsie[ :]+(8[\\\\w\\\\.]+)\"),\n" +
                "    Msie9(\"msie9\",\"IE9\",\"Internet Explorer 9\",\"\\\\bmsie[ :]+(9[\\\\w\\\\.]+)\"),\n" +
                "    Msie10(\"msie10\",\"IE10\",\"Internet Explorer 10\",\"\\\\bmsie[ :]+(10[\\\\w\\\\.]+)\"),";
        Pattern pattern = Pattern.compile("(\\w+)\\((\".*\")[, ]+(\".*\")[, ]+(\".*\")[, ]+(\".*\")\\)[;,]",Pattern.CASE_INSENSITIVE);
        String[] lines = text.split("\n");
        for (String line : lines) {
//            System.out.println(line);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()){
                //System.out.println(line);
                System.out.println(matcher.group(1).toUpperCase()+"("+matcher.group(2)+","+matcher.group(3)+","+matcher.group(4)+","+matcher.group(5)+"),");
            } else {
                System.out.println("false");
            }
        }
    }
}
