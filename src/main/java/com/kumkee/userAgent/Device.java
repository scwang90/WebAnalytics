package com.kumkee.userAgent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 设备枚举
 * Created by Administrator on 2015/9/28.
 */
public enum Device {

    Tablet("tablet","TB","平板","ipad|pad\\b"),
    Mobile("mobile","MB","手机","mobile|android|phone"),
    Computer("computer","PC","电脑",".*"),

    Unknown("unknown","UN","未知",""),

    ;
    private final Pattern pattern;
    private final String name;
    private final String acronym;
    private final String remark;

    Device(String name, String acronym ,String remark,String pattern){
        this.name = name;
        this.remark = remark;
        this.acronym = acronym;
        this.pattern = Pattern.compile(pattern,Pattern.CASE_INSENSITIVE);
    }

    public static Device parser(String useragent){
        for (Device app:values()){
            Matcher matcher = app.pattern.matcher(useragent);
            if (matcher.find()){
                return app;
            }
        }
        return Device.Unknown;
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

}
