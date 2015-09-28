package com.kumkee.userAgent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Browser {

    Sougou("sougou","SG","搜狗浏览器","\\bse\\b[/ ]?([\\w\\.\\-]+)"),
    Qihu("360","360","360浏览器","360se/([\\w\\.\\-]+)"),
    Weixin("micromessenger","WX","微信浏览器","micromessenger/([\\w\\.\\-]+)"),
    Baidu("baidu","BD","百度浏览器","baidu\\w*/([\\w\\.\\-]+)"),
    QQBrowser("qq","QQ","QQ浏览器","\\bqq\\w*/([\\w\\.\\-]+)"),
    Xiaomi("xiaomi","XM","小米浏览器","xiaomi\\w*/([\\w\\.\\-]+)"),
    Miui("miui","MU","MIUI浏览器","miui\\w*/([\\w\\.\\-]+)"),
    Edge("edge","EDGE","微软 EDGE","\\bedge[ /]?([\\w\\.\\-]*)"),
    Opera("opera","OPR","欧鹏浏览器","opera[ /]?([\\w\\.\\-]*)|opr[ /]?([\\w\\.\\-]*)"),
    Firefox("firefox","FFOX","火狐浏览器","firefox/([\\w\\.\\-]*)"),
    MSIEMobile("MSIEMobile","IEMB","IE移动版","iemobile/([\\w\\.\\-]+)"),
    Msie11("msie11","IE11","Internet Explorer 11","\\bmsie[ :]+(11[\\w\\.\\-]+)|rv:(1[\\w\\.\\-]+)"),
    Msie10("msie10","IE10","Internet Explorer 10","\\bmsie[ :]+(10[\\w\\.\\-]+)"),
    Msie5("msie5","IE5","Internet Explorer 5","\\bmsie[ :]+(5[\\w\\.\\-]+)"),
    Msie6("msie6","IE6","Internet Explorer 6","\\bmsie[ :]+(6[\\w\\.\\-]+)"),
    Msie7("msie7","IE7","Internet Explorer 7","\\bmsie[ :]+(7[\\w\\.\\-]+)"),
    Msie8("msie8","IE8","Internet Explorer 8","\\bmsie[ :]+(8[\\w\\.\\-]+)"),
    Msie9("msie9","IE9","Internet Explorer 9","\\bmsie[ :]+(9[\\w\\.\\-]+)"),
    Konqueror("konqueror","KQE","Internet Explorer 9","konqueror[ /]?([\\w\\.\\-]+)"),
    Playstation3("konqueror3","PS3","playstation 3","playstation (3)"),
    PlaystationP("konquerorP","PSP","playstation portable","playstation (portable)"),
    LotusNotes("lotusnotes","LTNT","Lotus-Notes","lotus.notes/([\\w\\.\\-]+)"),
    ThunderBird("thunderbird","TDBD","火鸟浏览器","thunderbird/([\\w\\.\\-]+)"),
    Netscape("netscape","NETP","Netscape","netscape/([\\w\\.\\-]+)"),
    SeamonKey("seamonkey","SEAK","Seamonkey","seamonkey/([\\w\\.\\-]+)"),
    OutLook("outlook","OUTL","OutLook","microsoft.outlook/([\\w\\.\\-]+)"),
    Evolution("evolution","EVOT","Evolution","evolution/([\\w\\.\\-]+)"),
    Gabble("gabble","GABE","Gabble","gabble/([\\w\\.\\-]+)"),
    AdobeAir("adobeair","ADBA","AdobeAir","adobeair/([\\w\\.\\-]+)"),
    HttpClient("httpclient","APHC","AdobeAir","apache.httpclient/([\\w\\.\\-]+)"),
    Yammer("yammer","YAME","Yammer","Yammer[ /]+([\\w\\.\\-]+)"),
    BlackBerry("blackberry","BLBE","BlackBerry","blackberry/([\\w\\.\\-]+)"),
    Chrome("chrome","CHO","谷歌浏览器","chrome/([\\w\\.\\-]*)"),
    Safari("safari","SF","苹果 Safari","safari/([\\w\\.\\-]*)"),

    Unknown("unknown","UN","未知","");

    private final Pattern pattern;
    private final String name;
    private final String acronym;
    private final String remark;
    private String version;

    Browser(String name, String acronym, String remark, String pattern) {
        this.version = "";
        this.name = name;
        this.remark = remark;
        this.acronym = acronym;
        this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }

    public static Browser parser(String useragent) {
        for (Browser app : values()) {
            if (app.matches(useragent)) {
                return app;
            }
        }
        return Browser.Unknown;
    }

    private boolean matches(String useragent) {
        Matcher matcher = pattern.matcher(useragent);
        if (matcher.find()) {
            int index = 0;
            while ((version == null || version.trim().length() == 0) && index++ < matcher.groupCount()) {
                version = matcher.group(index);
            }
            version = (version == null) ? "" : version;
            return true;
        }
        return false;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public String getVersion() {
        return version;
    }

    public String getAcronym() {
        return acronym;
    }
}

