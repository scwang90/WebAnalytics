package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.EventDao;
import com.simpletech.webanalytics.model.Event;

/**
 * 数据库表t_event的Dao实现
 * @author 树朾
 * @date 2015-10-13 10:15:55 中国标准时间
 */
@Repository
public class EventDaoImpl extends BaseDaoImpl<Event> implements EventDao{

	@Override
	public int insert(Event t) {
		return super.insert(t);
	}

	@Override
	public int update(Event t) {
		return super.update(t);
	}

	@Override
	public int delete(Object id) {
		return super.delete(id);
	}

	@Override
	public int countAll() {
		return super.countAll();
	}

	@Override
	public Event findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<Event> findAll() {
		return super.findAll();
	}

	@Override
	public List<Event> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

