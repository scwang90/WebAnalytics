package com.kumkee.userAgent;

import java.util.regex.Pattern;

/**
 * 品牌识别
 * Created by SCWANG on 2015-09-27.
 */
public enum Model {
    HT("htc","HTC","htc"),
    LG("lg","LG","\\blg\\b"),
    SS("samsung","三星","samsung"),
    SI("sonyericsson","索爱","sonyericsson"),
    SY("sony","索尼","sony"),
    AS("asus","华硕","asus"),
    OA("onda","昂达","onda"),
    WT("woxter","WOXTER","woxter"),
    HW("huawei","华为","huawei"),
    DL("dell","戴尔","dell"),
    AH("archos","爱可视","archos"),
    MT("motorola","摩托罗拉","motorola"),
    //htc|lg|samsung|sonyericsson|sony|asus|onda|woxter|huawei|dell|archos|motorola
    XM("xiaomi","小米","\\bmi\\b|xiaomi"),
    AP("apple","苹果","apple\\b|\\bios\\b|iphone|ipad|\\bmac\\b"),
    NK("nokia","诺基亚","nokia|symbian"),
    PC("computer","电脑","windows|linux|wow"),
    UN("","未知",""),
    ;
    private final String name;
    private final String remark;
    private final Pattern pattern;

    Model(String name,String remark,String pattern){
        this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        this.name = name;
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public String getRemark() {
        return remark;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public static Model parser(String useragent){
        for (Model model:values()){
            if (model.pattern.matcher(useragent).find()){
                return model;
            }
        }
        return Model.UN;
    }
}
