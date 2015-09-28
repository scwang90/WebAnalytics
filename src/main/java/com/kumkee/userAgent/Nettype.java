package com.kumkee.userAgent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络类型枚举
 * Created by Administrator on 2015/9/28.
 */
public enum Nettype {

    Wifi("wifi","WF", "无线网络", "nettype/(wifi)"),
    G2("2g","G2", "2G流量", "nettype/(2g)\\b"),
    G3("3g","G3", "3G流量", "nettype/(3g)\\b"),
    G4("4g","G4", "4G流量", "nettype/(4g)\\b"),
    BroadBand("BroadBand","BB","宽带","windows nt|wow"),

    Unknown("unknown","UN", "未知", "nettype/(.*)\\b");

    private final Pattern pattern;
    private final String name;
    private final String remark;
    private final String acronym;
    private String value;

    Nettype(String name, String acronym, String remark, String pattern) {
        this.value = "";
        this.name = name;
        this.remark = remark;
        this.acronym = "";//acronym;
        this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }

    public static Nettype parser(String useragent) {
        for (Nettype app : values()) {
            if (app.matches(useragent)){
                return app;
            }
        }
        Nettype.Unknown.value = "";
        return Nettype.Unknown;
    }

    private boolean matches(String useragent) {
        Matcher matcher = pattern.matcher(useragent);
        if (matcher.find()) {
            int index = 0;
            while ((value == null || value.trim().length() == 0) && index++ < matcher.groupCount()) {
                value = matcher.group(index);
            }
            value = (value == null) ? "" : value;
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

    public String getValue() {
        return value;
    }

    public String getAcronym() {
        return acronym;
    }
}
