package com.useragent.target;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 网络类型枚举
 * Created by 树朾 on 2015/9/28.
 */
public enum Nettype {

    Wifi("wifi","WF", "WIFI无线网络", "nettype/(wifi)"),
    NonWifi("nonwifi","NWF", "非WIFI无线网络", "nettype/(nonwifi)"),
    CMNET("cmnet","CMNET", "中国移动", "nettype/(cmnet)"),
    CTWAP("ctwap","CTWAP", "中国电信 CTWAP", "nettype/(ctwap)"),
    CTNET("ctnet","CTNET", "中国电信 CTNET", "nettype/(ctnet)"),
    CTLTE("ctlte","CTLTE", "中国电信 CTLTE 4G", "nettype/(ctlte)"),
    G2("2g","G2", "2G流量", "nettype/(2g\\w*)"),
    G3("3g","G3", "3G流量", "nettype/(3g\\w*)"),
    G4("4g","G4", "4G流量", "nettype/(4g\\w*)"),
    BroadBand("BroadBand","BB","宽带","windows nt|wow"),

    Unknown("unknown","UN", "未知", "nettype/([\\w\\-\\.]*)");

    private final Pattern pattern;
    private final String name;
    private final String remark;
    private final String acronym;

    public static class Model{
        private Nettype value;
        private String deputy;
        public Model(Nettype value,String deputy){
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
        public String getValue() {
            return deputy;
        }
        public Nettype getNettype() {
            return value;
        }
    }

    Nettype(String name, String acronym, String remark, String pattern) {
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
        for (Nettype value : values()) {
            model = value.matches(useragent);
            if (model != null) {
                return model;
            }
        }
        return new Model(Nettype.Unknown, "");
    }

    public static Nettype parserAcronym(String acronym) {
        for (Nettype net : values()) {
            if (net.acronym.equals(acronym)) {
                return net;
            }
        }
        return Nettype.Unknown;
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
            return new Nettype.Model(this, deputy);
        }
        return null;
    }
}
