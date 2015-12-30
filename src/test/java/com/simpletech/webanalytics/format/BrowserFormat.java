package com.simpletech.webanalytics.format;

import com.useragent.target.Browser;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 浏览器识别测试
 * Created by SCWANG on 2015-09-27.
 */
public class BrowserFormat {

    @Test
    public void parser(){
        String useragent = "Mozilla/5.0 (Linux; U; Android 4.2.2; zh-TW; H30-U10 Build/JDQ39) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 UCBrowser/10.5.0.575 U3/0.8.0 Mobile Safari/534.30";
        Pattern pattern = Pattern.compile("iemobile/([\\w\\.\\-]+)|windows phone[ /]?([\\w\\.\\-]+)?",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(useragent);
        System.out.println(matcher.find());
//        System.out.println(matcher.group());
//        System.out.println(matcher.group(1));
        System.out.println(Browser.parser(useragent).getRemark());
        System.out.println(Browser.parser(useragent).getVersion());
    }

    @Test
    public void regex(){
        String useragent = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.6 Safari/537.36 OPR/34.0.1996.0 (Edition developer)";
        Pattern pattern = Pattern.compile("opr/([\\w\\.]*)",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(useragent);
        System.out.println(matcher.find());
        System.out.println(matcher.group());
        System.out.println(matcher.group(1));
    }

}
