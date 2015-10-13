package com.simpletech.webanalytics;

import com.webanalytics.useragent.OperateSystem;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * 操作系统测试
 * Created by SCWANG on 2015-09-27.
 */
public class OperateFormat {

    @Test
    public void parser(){
        String useragent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_10_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
        System.out.println(Pattern.compile("\\bmi\\b|xiaomi",Pattern.CASE_INSENSITIVE).matcher(useragent).find());
        System.out.println(OperateSystem.parser(useragent).getRemark());
        System.out.println(OperateSystem.parser(useragent).getVersion());
    }

}
