package com.simpletech.webanalytics.format;

import com.useragent.target.OperateSystem;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * 操作系统测试
 * Created by SCWANG on 2015-09-27.
 */
public class OperateFormat {

    @Test
    public void parser(){
        String useragent = "Mozilla/5.0 (iPhone; CPU iPhone OS 8_4 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12H143 MicroMessenger/6.2.6 NetType/WIFI Language/zh_CN";
        System.out.println(Pattern.compile("\\bmi\\b|xiaomi",Pattern.CASE_INSENSITIVE).matcher(useragent).find());
        System.out.println(OperateSystem.parser(useragent).getRemark());
        System.out.println(OperateSystem.parser(useragent).getVersion());
    }

}
