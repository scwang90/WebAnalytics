package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.ShareUser;
import com.simpletech.webanalytics.model.Title;
import com.simpletech.webanalytics.model.Url;
import com.simpletech.webanalytics.model.entity.JsDetect;

/**
 * 分享传播分析 接收服务
 * Created by 树朾 on 2015/9/22.
 */
public interface TrackShareService {

    /**
     * 分享追踪
     *  @param siteId 网站ID
     * @param idsubsite 子站ID
     * @param detect    JS探针数据
     * @param url       URl页面
     * @param title     标题对象
     */
    void trackerShare(int siteId, String idsubsite, JsDetect detect, Url url, Title title);

    /**
     * 添加或更新 user
     * @param user JsUser
     */
    void addOrUpdateUser(int siteId, ShareUser user);
}
