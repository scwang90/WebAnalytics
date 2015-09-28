package com.kumkee.userAgent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * App名称
 * Created by Administrator on 2015/9/28.
 */
public enum Application {

    QQ("qq","QQ","腾讯QQ","\\bqq/(.*)\\b"),
    Weixin("weixin","WX","微信","\\bmicromessenger/(.*)\\b"),
    Computer("computer","PC","电脑","windows nt|wow"),

    Unknown("unknown","UN","未知","");
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
        return Application.Unknown;
    }

    private boolean matches(String useragent) {
        if (this.equals(Computer) && Device.parser(useragent).equals(Device.Computer)) {
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
