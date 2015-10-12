package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.EventDao;
import com.simpletech.webanalytics.model.Event;

/**
 * 数据库表t_event的Dao实现
 * @author 树朾
 * @date 2015-10-12 15:00:31 中国标准时间
 */
@Repository
public class EventDaoImpl extends BaseDaoImpl<Event> implements EventDao{

	@Override
	public int insert(Event t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(Event t) throws Exception {
		return super.update(t);
	}

	@Override
	public int delete(Object id) throws Exception {
		return super.delete(id);
	}

	@Override
	public int countAll() throws Exception {
		return super.countAll();
	}

	@Override
	public Event findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Event> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<Event> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

