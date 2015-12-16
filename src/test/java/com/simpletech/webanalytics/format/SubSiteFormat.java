package com.simpletech.webanalytics.format;

import com.simpletech.webanalytics.model.ShareLinePoint;
import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.util.AfStringUtil;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 子站判定测试
 * Created by Administrator on 2015/10/14.
 */
public class SubSiteFormat {

    @Test
    public void format() {
        System.out.println(getIdSubSite("\\//(.+)\\.xiao8web\\.","http://2.xiao8web.com/MicroSiteDisplay/display/47ef545b-f56c-49dc-9818-9955091ee6b4"));
    }

    private String getIdSubSite(String regex, String url) {
        String idsubsite = "";//
        if (AfStringUtil.isNotEmpty(regex) && url != null) {
            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                int index = 0;
                while ((idsubsite == null || idsubsite.trim().length() == 0) && index++ < matcher.groupCount()) {
                    idsubsite = matcher.group(index);
                }
                idsubsite = (AfStringUtil.isEmpty(idsubsite)) ? "" : idsubsite;
            }
        }
        return idsubsite;
    }

}
