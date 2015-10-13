package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.UrlDao;
import com.simpletech.webanalytics.model.Url;

/**
 * 数据库表t_url的Dao实现
 * @author 树朾
 * @date 2015-10-13 10:15:55 中国标准时间
 */
@Repository
public class UrlDaoImpl extends BaseDaoImpl<Url> implements UrlDao{

	@Override
	public int insert(Url t) throws Exception {
		return super.insert(t);
	}

	@Override
	public int update(Url t) throws Exception {
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
	public Url findById(Object id) throws Exception {
		return super.findById(id);
	}

	@Override
	public List<Url> findAll() throws Exception {
		return super.findAll();
	}

	@Override
	public List<Url> findByPage(int limit, int start) throws Exception {
		return super.findByPage(limit, start);
	}
}

