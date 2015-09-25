package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.entity.JsDetect;
import com.simpletech.webanalytics.model.entity.JsEvent;

/**
 * JS探针 接收服务
 * Created by Administrator on 2015/9/22.
 */
public interface TrackerService {
    /**
     * JS探针页面统计
     * @param idsite 网站ID
     * @param detect 页面参数接收对象
     * @throws Exception
     */
    void trackerPageView(String idsite, JsDetect detect) throws Exception;

    /**
     * JS探针事件统计
     * @param idsite 网站ID
     * @param event 事件参数接收对象
     * @throws Exception
     */
    void trackerEvent(String idsite, JsEvent event) throws Exception;
}
