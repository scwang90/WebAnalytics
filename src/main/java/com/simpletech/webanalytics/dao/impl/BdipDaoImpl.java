package com.simpletech.webanalytics.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.BdipDao;
import com.simpletech.webanalytics.model.Bdip;

/**
 * 数据库表t_bdip的Dao实现
 * @author 树朾
 * @date 2015-12-08 12:53:52 中国标准时间
 */
@Repository
public class BdipDaoImpl extends BaseDaoImpl<Bdip> implements BdipDao{

	@Override
	public int insert(Bdip t) {
		return super.insert(t);
	}

	@Override
	public int update(Bdip t) {
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
	public Bdip findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<Bdip> findAll() {
		return super.findAll();
	}

	@Override
	public List<Bdip> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}
}

