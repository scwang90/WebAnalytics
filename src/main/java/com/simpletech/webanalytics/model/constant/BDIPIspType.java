package com.simpletech.webanalytics.model.constant;

/**
 * 百度运营商类型
 * Created by 树朾 on 2015/10/8.
 */
public enum BDIPIspType {
    CHINANET("电信"), UNICOM("联通"), CRTC("铁通"), CSTNET("科技网"), CERNET("教育网"), NET("天威视讯"), CHINAGBN("金桥网"), UNINET("联通"), CNCNET("网通"), CMNET("移动"), CIETNET("经贸网"), CGWNET("长城"), CSNET("卫星网"), ONTHOR("其他");

    public final String value;

    BDIPIspType(String value) {
        this.value = value;
    }
}
