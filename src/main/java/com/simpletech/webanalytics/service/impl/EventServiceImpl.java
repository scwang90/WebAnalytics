package com.simpletech.webanalytics.service.impl;

import com.simpletech.webanalytics.dao.EventDao;
import com.simpletech.webanalytics.model.Event;
import com.simpletech.webanalytics.model.Site;
import com.simpletech.webanalytics.model.entity.JsEvent;
import com.simpletech.webanalytics.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 数据库表t_event的Service接实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Service
public class EventServiceImpl extends BaseServiceImpl<Event> implements EventService{

	@Autowired
	EventDao dao;

	@Override
	public void trackerEvent(Site site, JsEvent jsevent) throws Exception {
		dao.insert(jsevent.buildEvent(site.getId()));
	}
}
