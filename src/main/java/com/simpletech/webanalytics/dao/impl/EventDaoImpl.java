package com.simpletech.webanalytics.dao.impl;

import com.simpletech.webanalytics.dao.EventDao;
import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.mapper.StatisticsMapper;
import com.simpletech.webanalytics.model.Event;
import com.simpletech.webanalytics.model.entity.EventPeriodValue;
import com.simpletech.webanalytics.model.entity.EventValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 数据库表t_event的Dao实现
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Repository
public class EventDaoImpl extends BaseDaoImpl<Event> implements EventDao{

	@Autowired
	StatisticsMapper mapper;

	@Override
	public List<EventValue> event(String idsite, Date start, Date end, int limit, int skip) throws Exception {
		return mapper.event(idsite, start, end, limit, skip);
	}

	@Override
	public List<EventPeriodValue> eventMonth(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception {
		return mapper.eventMonth(idsite, category, start, end, limit, skip);
	}

	@Override
	public List<EventPeriodValue> eventWeek(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception {
		return mapper.eventWeek(idsite, category, start, end, limit, skip);
	}

	@Override
	public List<EventPeriodValue> eventDay(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception {
		return mapper.eventDay(idsite, category, start, end, limit, skip);
	}

	@Override
	public List<EventPeriodValue> eventHour(String idsite, String category, Date start, Date end, int limit, int skip) throws Exception {
		return mapper.eventHour(idsite, category, start, end, limit, skip);
	}
}

