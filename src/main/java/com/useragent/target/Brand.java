package com.useragent.target;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 品牌识别
 * Created by SCWANG on 2015-09-27.
 */
public enum Brand {

    //Mozilla/5.0 (Linux; U; Android 5.1; zh-cn; m1 note Build/LMY47D) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025469 Mobile Safari/533.1 V1_AND_SQ_5.9.1_272_YYB_D QQ/5.9.1.2535 NetType/WIFI WebP/0.3.0 Pixel/1080
    //Mozilla/5.0 (Linux; U; Android 4.2.2; zh-cn; vivo Y15T Build/JDQ39) AppleWebKit/533.1 (KHTML, like Gecko)Version/4.0 MQQBrowser/5.4 TBS/025469 Mobile Safari/533.1 MicroMessenger/6.2.2.54_rec1912d.581 NetType/WIFI Language/zh_CN
    Htc("htc", "HT", "HTC", "\\b(htc[\\w ]+) build\\b"),
    Htc1("htc", "HT", "HTC", "htc"),
    Lg("lg", "LG", "LG", "\\blg\\b"),
    Sonyericsson("sonyericsson", "SI", "索爱", "sonyericsson"),
    Sony("sony", "SY", "索尼", "sony"),
    Asus("asus", "AS", "华硕", "asus"),
    Onda("onda", "OA", "昂达", "onda"),
    Woxter("woxter", "WT", "Woxter", "woxter"),
    Dell("dell", "DL", "戴尔", "dell"),
    Archos("archos", "AH", "爱可视", "archos"),

    Samsung("samsung", "SS", "三星", "\\b(gt-.+) build|(EK-G\\S*)|samsung[ -]?([\\w\\-]+)"),
    Samsung1("samsung", "SS", "三星", "\\b(X900) build\\b"),
    Samsung2("samsung", "SS", "三星", "(samsung)"),

    Motorola("motorola", "MT", "摩托罗拉", "\\b(mot[\\w\\- ]*|mb[\\w\\- ]+|xoom[\\w\\- ]*|xt[\\w\\- ]*) build"),
    Motorola1("motorola", "MT", "摩托罗拉", "\\b(xt[\\w\\- ]*)/[\\w\\-\\.]+\\)"),
    Motorola2("motorola", "MT", "摩托罗拉", "(motorola)"),

    //htc|lg|samsung|sonyericsson|sony|asus|onda|woxter|huawei|dell|archos|motorola

    Vivo("vivo", "VIVO", "Vivo", "\\b(vivo.+) build\\b"),
    ZTE("ZTE", "ZTE", "中兴", "\\b(zte.+) build\\b"),

    XiaoMi("xiaomi", "XM", "小米", "\\b(mi [\\w\\- ]+|hm [\\w\\- ]+) build\\b"),
    XiaoMi1("xiaomi", "XM", "小米", "\\b(2014813) build\\b"),
    XiaoMi2("xiaomi", "XM", "小米", "build/(hm\\d+)"),
    XiaoMi3("xiaomi", "XM", "小米", "(xiaomi|miui)"),

    Nubia("nubia","NBY","奴努比亚","\\b(nx[\\w ]+) build\\b"),

    Smartisan("smartisan", "SM", "锤子", "\\b(sm.+) build\\b"),

    Nokia("nokia", "NK", "诺基亚", "nokia;\\s*(\\S+\\s*\\S+)|(nokia\\s*\\S+)"),
    Nokia1("nokia", "NK", "诺基亚", "(symbian|nokia)"),

    Apple("apple", "AP", "苹果", "(iphone)|(ipad)|\\b(mac)\\b"),
    Apple1("apple", "AP", "苹果", "(apple)\\b|\\b(ios)\\b"),

    Hisense("hisense", "HS", "海信", "\\b(hs-[\\w\\- ]+)\\b"),

    GiONEE("gionee", "GINE", "金立", "\\b(gionee-[\\w\\- ]+)\\b"),


    Huawei("huawei", "HW", "华为", "\\b(pe-[\\w\\- ]+|plk-[\\w\\- ]+) build\\b"),
    Huawei1("huawei", "HW", "华为", "\\bhuawei ([\\w\\- ]+) build\\b"),
    Huawei2("huawei", "HW", "华为", "\\bbuild/huawei([\\w\\- ]+)"),
    Huawei3("huawei", "HW", "华为", "\\b(h[\\w\\- ]+) build\\b"),
    Huawei4("huawei", "HW", "华为", "\\bhuawei ([\\w\\- ]+)\\b"),
    Huawei5("huawei", "HW", "华为", "\\b(HONOR[_\\-][\\w\\- ]+)\\b"),

    Oppo("oppo","OPPO","欧珀 OPPO","\\b(r6[\\w\\-\\. ]+) build\\b"),

    Meizu("meizu", "MZ", "魅族", "\\b(m[\\w\\-\\. ]+) build\\b"),

    Unknown("unknown", "UN", "未知", "\\b([\\w\\-\\. ]+) build\\b"),
    Unknown2("unknown", "UN", "未知", "android"),
    Computer("computer", "PC", "电脑", "windows nt|\\bwow\\b|linux");

    private final String name;
    private final String remark;
    private final String acronym;
    private final Pattern pattern;

    public static class Model {
        private Brand value;
        private String deputy;
        public Model(Brand value, String deputy) {
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
        public String getModel() {
            return deputy;
        }
        public Brand getValue() {
            return value;
        }
    }

    Brand(String name, String acronym, String remark, String pattern) {
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
    public String getAcronym() {
        return acronym;
    }

    public static Model parser(String useragent) {
        Model model;
        for (Brand value : values()) {
            model = value.matches(useragent);
            if (model != null) {
                return model;
            }
        }
        return new Model(Brand.Unknown, "");
    }

    public static Brand parserAcronym(String acronym) {
        for (Brand brand : values()) {
            if (brand.acronym.equals(acronym)) {
                return brand;
            }
        }
        return Brand.Unknown;
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
            return new Brand.Model(this, deputy);
        }
        return null;
    }

}
