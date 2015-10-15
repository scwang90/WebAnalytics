package com.simpletech.webanalytics.model.entity;

import com.simpletech.webanalytics.annotations.Must;
import com.simpletech.webanalytics.model.ShareUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * JS探针发送的数据格式+服务器获取数据
 */
public class JsUser extends ShareUser{

    /**
     * 数据收集相关
     */
    @Must("当前页面的完整URL")
    private String url;     //(必需) 当前页面的完整URL

    public void bind(HttpServletRequest request, HttpServletResponse response) {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}