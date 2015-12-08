package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.SubsiteDao;
import com.simpletech.webanalytics.model.Subsite;

/**
 * 数据库表t_subsite的Dao实现
 * @author 树朾
 * @date 2015-10-13 10:15:55 中国标准时间
 */
@Repository
public class SubsiteDaoImpl extends BaseDaoImpl<Subsite> implements SubsiteDao{

	@Override
	public int insert(Subsite t) {
		return super.insert(t);
	}

	@Override
	public int update(Subsite t) {
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
	public Subsite findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<Subsite> findAll() {
		return super.findAll();
	}

	@Override
	public List<Subsite> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

