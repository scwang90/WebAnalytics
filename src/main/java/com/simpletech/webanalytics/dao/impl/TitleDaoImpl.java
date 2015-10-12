package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.TitleDao;
import com.simpletech.webanalytics.model.Title;

/**
 * 数据库表t_title的Dao实现
 * @author 树朾
 * @date 2015-10-12 15:00:31 中国标准时间
 */
@Repository
public class TitleDaoImpl extends BaseDaoImpl<Title> implements TitleDao{

	@Override
	public int insert(Title t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(Title t) throws Exception {
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
	public Title findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Title> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<Title> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

