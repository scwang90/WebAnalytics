package com.webanalytics.useragent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 品牌识别
 * Created by SCWANG on 2015-09-27.
 */
public enum Brand {

    //Mozilla/5.0 (Linux; U; Android 5.1; zh-cn; m1 note Build/LMY47D) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025469 Mobile Safari/533.1 V1_AND_SQ_5.9.1_272_YYB_D QQ/5.9.1.2535 NetType/WIFI WebP/0.3.0 Pixel/1080
    //Mozilla/5.0 (Linux; U; Android 4.2.2; zh-cn; vivo Y15T Build/JDQ39) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025469 Mobile Safari/533.1 MicroMessenger/6.2.2.54_rec1912d.581 NetType/WIFI Language/zh_CN
    Htc("htc","HT","HTC","htc"),
    Lg("lg","LG","LG","\\blg\\b"),
    Sonyericsson("sonyericsson","SI","索爱","sonyericsson"),
    Sony("sony","SY","索尼","sony"),
    Asus("asus","AS","华硕","asus"),
    Onda("onda","OA","昂达","onda"),
    Woxter("woxter","WT","Woxter","woxter"),
    Huawei("huawei","HW","华为","huawei"),
    Dell("dell","DL","戴尔","dell"),
    Archos("archos","AH","爱可视","archos"),

    Samsung("samsung","SS","三星","(EK-G\\S*)"),
    Samsung1("samsung","SS","三星","(samsung)"),

    Motorola("motorola","MT","摩托罗拉","\\b(mot\\S*|mb\\S*|xoom\\S*) build"),
    Motorola1("motorola","MT","摩托罗拉","(motorola)"),

    //htc|lg|samsung|sonyericsson|sony|asus|onda|woxter|huawei|dell|archos|motorola

    Vivo("vivo","VIVO","Vivo","\\b(vivo\\S*) build"),

    XiaoMi("xiaomi","XM","小米","\\b(mi ?\\S*|hm ?\\S*) build"),
    XiaoMi1("xiaomi","XM","小米","(xiaomi|miui)"),

    Smartisan("smartisan","SM","锤子","\\b(sm\\S*) build\\b"),

    Nokia("nokia","NK","诺基亚","(nokia\\s*[\\w\\.\\-]+)"),
    Nokia1("nokia","NK","诺基亚","(symbian)"),

    Apple("apple","AP","苹果","(iphone)|(ipad)|\\b(mac)\\b"),
    Apple1("apple","AP","苹果","(apple)\\b|\\b(ios)\\b"),

    Meizu("meizu","MZ","魅族","\\b(m\\S*) build\\b"),
    Computer("computer","PC","电脑","windows nt|wow"),

    Unknown("unknown","UN","未知","\\b(\\S*) build\\b");

    private final String name;
    private final String remark;
    private final String acronym;
    private final Pattern pattern;
    private String model;

    Brand(String name, String acronym, String remark, String pattern){
        this.model = "";
        this.name = name;
        this.remark = remark;
        this.acronym = acronym;
        this.pattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
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
    public String getModel() {
        return model;
    }
    public String getAcronym() {
        return acronym;
    }

    public static Brand parser(String useragent){
        for (Brand brand:values()){
            if (brand.matches(useragent)){
                return brand;
            }
        }
        Brand.Unknown.model = "";
        return Brand.Unknown;
    }

    public static Brand parserAcronym(String acronym) {
        for (Brand brand : values()) {
            if (brand.acronym.equals(acronym)) {
                return brand;
            }
        }
        Brand.Unknown.model = "";
        return Brand.Unknown;
    }

    private boolean matches(String useragent) {
        Matcher matcher = pattern.matcher(useragent);
        if (matcher.find()){
            int index = 0;
            while ((model == null || model.trim().length() == 0) && index++ < matcher.groupCount()) {
                model = matcher.group(index);
            }
            model = (model == null) ? "" : model;
            return true;
        }
        return false;
    }
}
