package com.simpletech.webanalytics.format;

import com.useragent.target.Application;
import org.junit.Test;

import java.util.regex.Pattern;

/**
 * App格式测试
 * Created by SCWANG on 2015-10-03.
 */
public class ApplicationFormat {

    @Test
    public void format(){
        String useragent = "Mozilla/5.0 (Linux; Android 4.4.2; MI 3 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/30.0.0.0 Mobile Safari/537.36 Weibo (Xiaomi-MI 3__weibo__5.4.5__android__android4.4.2)";
        Application.Model parser = Application.parser(useragent);
        System.out.println(Pattern.compile("weibo__[\\d\\-\\.]+", Pattern.CASE_INSENSITIVE).matcher(useragent).find());
        System.out.println(parser.getRemark());
        System.out.println(parser.getVersion());
    }

}
