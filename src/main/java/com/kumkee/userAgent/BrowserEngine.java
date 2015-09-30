package com.kumkee.userAgent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BrowserEngine {

    Webkit("webkit","WK","Webkit","webkit[\\/\\- ]([\\w\\.\\-]+)"),
    Trident("trident","TRD","Trident","trident[\\/\\- ]([\\d\\w\\.\\-]+)"),
    Gecko("gecko","PT","Gecko","gecko[\\/\\- ]([\\d\\w\\.\\-]+)"),
    KHTML("khtml","KH","KHTML","khtml[\\/\\- ]([\\d\\w\\.\\-]+)"),
    Presto("presto","PT","Presto","presto[\\/\\- ]([\\d\\w\\.\\-]+)"),
    Konqeror("konqueror","KG","Konqeror","konqueror[\\/\\- ]([\\d\\w\\.\\-]+)"),
    Chrome("chrome","CH","Chrome","chrome[\\/\\- ]([\\d\\w\\.\\-]+)"),
    Opera("opera","OP","Opera","opera[\\/\\- ]([\\d\\w\\.\\-]+)"),
    MIDP("midp","MIDP","MIDP","midp[\\/\\- ]([\\d\\w\\.\\-]+)"),
    MSIE("msie","MSIE","MSIE","msie[\\/\\- ]([\\d\\w\\.\\-]+)"),

    Unknown("unknown","UN","未知","");

    private final Pattern pattern;
    private final String name;
    private final String acronym;
    private final String remark;
    private String version;

    BrowserEngine(String name, String acronym, String remark, String pattern) {
        this.version = "";
        this.name = name;
        this.remark = remark;
        this.acronym = acronym;
        this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    }

    public static BrowserEngine parser(String useragent) {
        for (BrowserEngine engine : values()) {
            if (engine.matches(useragent)) {
                return engine;
            }
        }
        BrowserEngine.Unknown.version = "";
        return BrowserEngine.Unknown;
    }

    public static BrowserEngine parserAcronym(String acronym) {
        for (BrowserEngine engine : values()) {
            if (engine.acronym.equals(acronym)) {
                return engine;
            }
        }
        BrowserEngine.Unknown.version = "";
        return BrowserEngine.Unknown;
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

