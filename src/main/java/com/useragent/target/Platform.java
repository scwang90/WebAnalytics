package com.useragent.target;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 设备枚举
 * Created by 树朾 on 2015/9/28.
 */
public enum Platform {

    Car("car","CAR","Car Browser","qtcarbrowser"),
    Tablet("tablet","TB","平板","ipad|pad\\b"),
    Camera("camera","CAM","相机","Coolpix S800c|EK-G[CN][0-9]{3}"),
    Mobile("mobile","MB","手机","mobile|android|phone"),
    Computer("computer","PC","电脑",".*"),

    Unknown("unknown","UN","未知",""),

    ;
    private final Pattern pattern;
    private final String name;
    private final String acronym;
    private final String remark;

    public static class Model{
        private Platform value;
        public Model(Platform value){
            this.value = value;
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
    }

    Platform(String name, String acronym, String remark, String pattern){
        this.name = name;
        this.remark = remark;
        this.acronym = acronym;
        this.pattern = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
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

    public static Model parser(String useragent){
        Model model;
        for (Platform value : values()) {
            model = value.matches(useragent);
            if (model != null) {
                return model;
            }
        }
        return new Model(Platform.Unknown);
    }

    private Model matches(String useragent) {
        Matcher matcher = pattern.matcher(useragent);
        if (matcher.find()) {
            return new Platform.Model(this);
        }
        return null;
    }

    public static Platform parserAcronym(String acronym) {
        for (Platform platform : values()) {
            if (platform.acronym.equals(acronym)) {
                return platform;
            }
        }
        return Platform.Unknown;
    }

}
