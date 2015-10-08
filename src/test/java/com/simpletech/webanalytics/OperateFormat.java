package com.simpletech.webanalytics;

import com.webanalytics.useragent.OperateSystem;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * Created by SCWANG on 2015-09-27.
 */
public class OperateFormat {

    @Test
    public void parser(){
        String useragent = "Mozilla/5.0 (iPad; CPU OS 8_4 like Mac OS X) AppleWebKit/600.1.4 (KHTML, like Gecko) Mobile/12H143 IPadQQ/5.4.0.127 QQ/5.4.0.127";
        System.out.println(Pattern.compile("\\bmi\\b|xiaomi",Pattern.CASE_INSENSITIVE).matcher(useragent).find());
        System.out.println(OperateSystem.parser(useragent).getRemark());
        System.out.println(OperateSystem.parser(useragent).getVersion());
    }

}
