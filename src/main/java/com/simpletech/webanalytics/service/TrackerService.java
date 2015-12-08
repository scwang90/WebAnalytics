package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.model.entity.JsEvent;
import com.simpletech.webanalytics.model.entity.JsUser;

/**
 * JS探针 接收服务
 * Created by 树朾 on 2015/9/22.
 */
public interface TrackerService {

    /**
     * JS探针事件统计
     * @param siteId 网站ID
     * @param event 事件参数接收对象
     * @throws Exception
     */
    void trackEvent(int siteId, JsEvent event);

    /**
     * JS探针页面统计
     * @param siteId 网站ID
     * @param detect 页面参数接收对象
     * @throws Exception
     */
    void trackPageView(int siteId, JsDetect detect);

    /**
     * JS探针用户信息
     *
     * @param siteId 网站ID
     * @param user  事件接收对象
     */
    void trackerUser(int siteId, JsUser user);
}
