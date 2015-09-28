package com.simpletech.webanalytics;

import com.kumkee.userAgent.Brand;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by SCWANG on 2015-09-27.
 */
public class ModelFormat {

    @Test
    public void parser(){
        String useragent = "Mozilla/5.0 (SymbianOS/9.4; Series60/5.0 NokiaN97-1/20.0.019; Profile/MIDP-2.1 Configuration/CLDC-1.1) AppleWebKit/525 (KHTML, like Gecko) BrowserNG/7.1.18124";
        Matcher matcher = Pattern.compile("\\b(m[\\w\\-\\. ]*) build\\b", Pattern.CASE_INSENSITIVE).matcher(useragent);
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
