package com.simpletech.webanalytics.model.constant;

/**
 * 分享去向类型
 * Created by 树朾 on 2015/10/8.
 */
public enum ShareToType {

    friend("朋友"),friends("朋友圈");

    public final String value;

    ShareToType(String value) {
        this.value = value;
    }
}
