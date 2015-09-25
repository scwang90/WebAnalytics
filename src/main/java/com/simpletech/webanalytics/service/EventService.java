package com.simpletech.webanalytics.service;

import com.simpletech.webanalytics.model.Event;
import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.model.entity.JsEvent;

/**
 * 数据库表t_event的Service接口层
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
public interface EventService extends BaseService<Event>{

	/**
	 * JS探针事件处理
	 * @param site 网站ID
	 * @param event JS接收事件对象
	 */
	void trackerEvent(Site site, JsEvent event) throws Exception;
}
