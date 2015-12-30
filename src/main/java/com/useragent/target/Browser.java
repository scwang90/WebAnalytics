package com.useragent.target;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Browser {

    Sougou("sougou", "SG", "搜狗浏览器", "\\bse\\b[/ ]?(\\S+)"),
    Qihu("360", "360", "360浏览器", "360se/(\\S+)"),
    Baidu("baidu", "BD", "百度浏览器", "baidu\\w*/(\\S+)"),
    Weibo("weibo", "WB", "新浪微博", "weibo__([\\d\\-\\.]+)"),

    QQ("qq", "QQ", "手机QQ浏览器", "\\bqq/(\\S+)"),
    Weixin("micromessenger", "WX", "微信浏览器", "micromessenger/(\\S+)"),
    Qzone("qzone", "QZONE", "QQ空间浏览器", "qzone/(\\S+)"),
    TXWeibo("txmicroblog", "TXWB", "腾讯微博", "txmicroblog[/ ]?(\\S+)"),
    MQQBrowser("mqqbrowser", "MQQB", "QQ浏览器手机版", "mqqbrowser/(\\S+)"),
    QQBrowser("qqbrowser", "QQB", "QQ浏览器", "qqbrowser/(\\S+)"),
    OppoBrowser("oppobrowser", "OPPO", "OPPO 浏览器", "oppobrowser/(\\S+)"),
    UCBrowser("ucbrowser", "UC", "UC浏览器", "ucbrowser/([\\d\\-\\.]+)"),

    Miui("miui", "MU", "MIUI浏览器", "miui\\w*/(\\S+)"),
    Xiaomi("xiaomi", "XM", "小米浏览器", "xiaomi\\w*/(\\S+)"),

    Edge("edge", "EDGE", "微软 Edge", "\\bedge[ /]?(\\S*)"),
    Opera("opera", "OPR", "欧鹏浏览器", "opera[ /]?(\\S*)|opr[ /]?(\\S*)"),
    Firefox("firefox", "FFOX", "火狐浏览器", "firefox/(\\S*)"),
    MSIEMobile("MSIEMobile", "IEMB", "IE移动版", "iemobile/([\\w\\.\\-]+)"),
    Msie11("msie11", "IE11", "Internet Explorer 11", "\\bmsie[ :]+(11[\\w\\.\\-]+)|rv:(1[\\w\\.\\-]+)"),
    Msie10("msie10", "IE10", "Internet Explorer 10", "\\bmsie[ :]+(10[\\w\\.\\-]+)"),
    Msie5("msie5", "IE5", "Internet Explorer 5", "\\bmsie[ :]+(5[\\w\\.\\-]+)"),
    Msie6("msie6", "IE6", "Internet Explorer 6", "\\bmsie[ :]+(6[\\w\\.\\-]+)"),
    Msie7("msie7", "IE7", "Internet Explorer 7", "\\bmsie[ :]+(7[\\w\\.\\-]+)"),
    Msie8("msie8", "IE8", "Internet Explorer 8", "\\bmsie[ :]+(8[\\w\\.\\-]+)"),
    Msie9("msie9", "IE9", "Internet Explorer 9", "\\bmsie[ :]+(9[\\w\\.\\-]+)"),
    Konqueror("konqueror", "KQE", "Internet Explorer 9", "konqueror[ /]?(\\S+)"),
    Playstation3("konqueror3", "PS3", "playstation 3", "playstation (3)"),
    PlaystationP("konquerorP", "PSP", "playstation portable", "playstation (portable)"),
    LotusNotes("lotusnotes", "LTNT", "Lotus-Notes", "lotus.notes/(\\S+)"),
    ThunderBird("thunderbird", "TDBD", "火鸟浏览器", "thunderbird/(\\S+)"),
    Netscape("netscape", "NETP", "Netscape", "netscape/(\\S+)"),
    SeamonKey("seamonkey", "SEAK", "Seamonkey", "seamonkey/(\\S+)"),
    OutLook("outlook", "OUTL", "OutLook", "microsoft.outlook/(\\S+)"),
    Evolution("evolution", "EVOT", "Evolution", "evolution/(\\S+)"),
    Gabble("gabble", "GABE", "Gabble", "gabble/(\\S+)"),
    AdobeAir("adobeair", "ADBA", "AdobeAir", "adobeair/(\\S+)"),
    HttpClient("httpclient", "APHC", "HttpClient", "apache.httpclient/(\\S+)"),
    Yammer("yammer", "YAME", "Yammer", "Yammer[ /]+(\\S+)"),
    BlackBerry("blackberry", "BLBE", "BlackBerry", "blackberry/(\\S+)"),
    Chrome("chrome", "CHO", "谷歌浏览器", "chrome/(\\S+)"),
    Safari("safari", "SF", "苹果 Safari", "safari/(\\S+)"),

    Unknown("unknown", "UN", "未知", "");

    private final Pattern pattern;
    private final String name;
    private final String acronym;
    private final String remark;

    public static class Model{
        private Browser value;
        private String deputy;
        public Model(Browser value,String deputy){
            this.value = value;
            this.deputy = deputy;
        }
        public String getName() {
            return value.name;
        }
        public String getRemark() {
            return value.remark;
        }
        public Pattern getPattern() {
            return value.pattern;
        }
        public String getAcronym() {
            return value.acronym;
        }
        public String getVersion() {
            return deputy;
        }
    }

    Browser(String name, String acronym, String remark, String pattern) {
        this.name = name;
        this.remark = remark;
        this.acronym = acronym;
        this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
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
    public String getAcronym() {
        return acronym;
    }

    public static Model parser(String useragent) {
        Model model;
        for (Browser value : values()) {
            model = value.matches(useragent);
            if (model != null) {
                return model;
            }
        }
        return new Model(Browser.Unknown, "");
    }

    public static Browser parserAcronym(String acronym) {
        for (Browser browser : values()) {
            if (browser.acronym.equals(acronym)) {
                return browser;
            }
        }
        return Browser.Unknown;
    }

    private Model matches(String useragent) {
        String deputy = null;
        Matcher matcher = pattern.matcher(useragent);
        if (matcher.find()) {
            int index = 0;
            while ((deputy == null || deputy.trim().length() == 0) && index++ < matcher.groupCount()) {
                deputy = matcher.group(index);
            }
            deputy = (deputy == null) ? "" : deputy;
            return new Browser.Model(this, deputy);
        }
        return null;
    }

}

