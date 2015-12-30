package com.useragent.target;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * App名称
 * Created by 树朾 on 2015/9/28.
 */
public enum Application {

    QQ("qq", "QQ", "腾讯QQ", "\\bqq/(\\S+)"),
    Qzone("qzone", "QZ", "QQ空间", "qzone/(\\S+)"),
    Weixin("micromessenger", "MM", "微信", "micromessenger/(\\S+)"),
    TXWeibo("txmicroblog", "TXWB", "腾讯微博", "txmicroblog[/ ]?(\\S+)"),
    Weibo("weibo", "WB", "新浪微博", "weibo__([\\d\\-\\.]+)"),
    Weibo1("weibo", "WB", "新浪微博", "(weibo)"),
    Computer("computer", "PC", "电脑", "windows nt|wow"),

    Unknown("unknown", "UN", "手机浏览器", "");

    private final Pattern pattern;
    private final String name;
    private final String remark;
    private final String acronym;

    public static class Model{
        private Application value;
        private String deputy;
        public Model(Application value,String deputy){
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

    Application(String name, String acronym, String remark, String pattern) {
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
        for (Application value : values()) {
            model = value.matches(useragent);
            if (model != null) {
                return model;
            }
        }
        return new Model(Application.Unknown, "");
    }

    public static Application parserAcronym(String acronym) {
        for (Application app : values()) {
            if (app.acronym.equals(acronym)) {
                return app;
            }
        }
        return Application.Unknown;
    }

    private Model matches(String useragent) {
        if (this.equals(Computer) && Platform.parser(useragent).equals(Platform.Computer)) {
            return new Model(this,"");
        }
        String deputy = null;
        Matcher matcher = pattern.matcher(useragent);
        if (matcher.find()) {
            int index = 0;
            while ((deputy == null || deputy.trim().length() == 0) && index++ < matcher.groupCount()) {
                deputy = matcher.group(index);
            }
            deputy = (deputy == null) ? "" : deputy;
            return new Application.Model(this, deputy);
        }
        return null;
    }
}
