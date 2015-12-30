package com.simpletech.webanalytics.format;

import com.useragent.target.Brand;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SCWANG on 2015-09-27.
 */
public class ModelFormat {

    @Test
    public void parser(){
        String useragent = "Mozilla/5.0 (Linux; U; Android 4.3; zh-cn; R6007 Build/JLS36C) AppleWebKit/534.30 (KHTML, like Gecko) Version/4.0 OppoBrowser/3.6.0 Mobile Safari/534.30";
        Matcher matcher = Pattern.compile("\\b([\\w\\-\\. ]+) build/", Pattern.CASE_INSENSITIVE).matcher(useragent);
        if (matcher.find()){
            System.out.println(true);
            System.out.println(matcher.group(1));
        } else {
            System.out.println(false);
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

}
