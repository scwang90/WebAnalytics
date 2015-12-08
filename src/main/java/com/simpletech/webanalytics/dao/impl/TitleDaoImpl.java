package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.TitleDao;
import com.simpletech.webanalytics.model.Title;

/**
 * 数据库表t_title的Dao实现
 * @author 树朾
 * @date 2015-10-13 10:15:55 中国标准时间
 */
@Repository
public class TitleDaoImpl extends BaseDaoImpl<Title> implements TitleDao{

	@Override
	public int insert(Title t) {
		return super.insert(t);
	}

	@Override
	public int update(Title t) {
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
	public Title findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<Title> findAll() {
		return super.findAll();
	}

	@Override
	public List<Title> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

