package com.webanalytics.useragent;

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
    private String version;

    Application(String name, String acronym, String remark, String pattern) {
        this.version = "";
        this.name = name;
        this.remark = remark;
        this.acronym = acronym;
        this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }

    public static Application parser(String useragent) {
        for (Application app : values()) {
            if (app.matches(useragent)) {
                return app;
            }
        }
        Application.Unknown.version = "";
        return Application.Unknown;
    }

    public static Application parserAcronym(String acronym) {
        for (Application app : values()) {
            if (app.acronym.equals(acronym)) {
                return app;
            }
        }
        Application.Unknown.version = "";
        return Application.Unknown;
    }

    private boolean matches(String useragent) {
        if (this.equals(Computer) && Platform.parser(useragent).equals(Platform.Computer)) {
            return true;
        }
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
