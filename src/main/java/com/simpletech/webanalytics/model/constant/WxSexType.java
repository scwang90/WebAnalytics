package com.simpletech.webanalytics.model.constant;

/**
 * 微信用户性别类型
 * Created by 树朾 on 2015/10/8.
 */
public enum WxSexType {

    nuknow("未知"),male("男"),female("女");

    public final String value;

    WxSexType(String value) {
        this.value = value;
    }
}
