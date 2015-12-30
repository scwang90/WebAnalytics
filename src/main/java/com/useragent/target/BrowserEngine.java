package com.useragent.target;

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

    public static class Model{
        private BrowserEngine value;
        private String deputy;
        public Model(BrowserEngine value,String deputy){
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

    BrowserEngine(String name, String acronym, String remark, String pattern) {
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
        for (BrowserEngine value : values()) {
            model = value.matches(useragent);
            if (model != null) {
                return model;
            }
        }
        return new Model(BrowserEngine.Unknown, "");
    }

    public static BrowserEngine parserAcronym(String acronym) {
        for (BrowserEngine engine : values()) {
            if (engine.acronym.equals(acronym)) {
                return engine;
            }
        }
        return BrowserEngine.Unknown;
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
            return new BrowserEngine.Model(this, deputy);
        }
        return null;
    }

}

